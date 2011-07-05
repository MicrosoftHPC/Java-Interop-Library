//------------------------------------------------------------------------------
// <copyright file="DataClient.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//      Implement DataClient class
// </summary>
//------------------------------------------------------------------------------
/*
JAVA INTEROP LIBRARY FOR WINDOWS HPC SERVER

Copyright (c) Microsoft Corporation.  All rights reserved.

This license governs use of the accompanying software. If you use the
software, you accept this license. If you do not accept the license, do not
use the software.

1. Definitions
The terms "reproduce," "reproduction," "derivative works," and "distribution"
have the same meaning here as under U.S. copyright law.
A "contribution. is the original software, or any additions or changes to
the software.
A "contributor. is any person that distributes its contribution under this
license.
"Licensed patents. are a contributor.s patent claims that read directly on
its contribution.

2. Grant of Rights
(A) Copyright Grant- Subject to the terms of this license, including the
license conditions and limitations in section 3, each contributor grants you
a non-exclusive, worldwide, royalty-free copyright license to reproduce its
contribution, prepare derivative works of its contribution, and distribute
its contribution or any derivative works that you create.
(B) Patent Grant- Subject to the terms of this license, including the license
conditions and limitations in section 3, each contributor grants you a
non-exclusive, worldwide, royalty-free license under its licensed patents to
make, have made, use, sell, offer for sale, import, and/or otherwise dispose
of its contribution in the software or derivative works of the contribution
in the software.

3. Conditions and Limitations
(A) No Trademark License- This license does not grant you rights to use any
contributors' name, logo, or trademarks.
(B) If you bring a patent claim against any contributor over patents that
you claim are infringed by the software, your patent license from such
contributor to the software ends automatically.
(C) If you distribute any portion of the software, you must retain all
copyright, patent, trademark, and attribution notices that are present in
the software.
(D) If you distribute any portion of the software in source code form,
you may do so only under this license by including a complete copy of this
license with your distribution. If you distribute any portion of the software
in compiled or object code form, you may only do so under a license that
complies with this license.
(E) The software is licensed "as-is." You bear the risk of using it. The
contributors give no express warranties, guarantees or conditions. You may
have additional consumer rights under your local laws which this license
cannot change. To the extent permitted under your local laws, the contributors
exclude the implied warranties of merchantability, fitness for a particular
purpose and non-infringement.
(F) Platform Limitation- The licenses granted in sections 2(A) & 2(B) extend
only to the software or derivative works that you create that operate with
Windows HPC Server.
*/

package com.microsoft.hpc.scheduler.session;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.AccessControlException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import com.microsoft.hpc.exceptions.DataErrorCode;
import com.microsoft.hpc.exceptions.DataException;

public class DataClient
{
    static Pattern fileSharePathPattern = Pattern
            .compile("^\\\\\\\\(\\w[-\\w]*)(\\\\[^<>:\"\"/\\\\|?*]+)+$");
    static String localHostName = "localhost";
    static String compressionFlag = "E7FE6B887495BE4F93CD243D8C1E06AC";
    int compressionHeaderLen = 36;
    int compressionBufferSize = 0x1000; // 4k
    boolean readonly;
    boolean writable;
    String pathToData;
    String id;
    String username;
    String password;
    String headnode;
    boolean hasNamespace = false;

    /**
     * Initialize a new instance of DataClient
     * 
     * @param pathToData
     * @param readOnly
     * @param compress
     */
    private DataClient(String pathToData, String id, boolean readOnly,
            String username, String password, String headnode) {
        this.pathToData = ConvertPath(pathToData);
        this.readonly = readOnly;
        this.writable = !readonly;
        this.id = id;
        this.username = username;
        this.password = password;
        this.headnode = headnode;
    }

    /**
     * Write raw bytes in a byte array into the DataClient
     * 
     * @param data
     * @throws IllegalStateException
     *             if the DataClient is opened for read
     * @throws DataException
     *             if there is any I/O error when writing the data
     */
    public void writeRawBytesAll(byte[] data) throws IllegalStateException,
            DataException {
        writeRawBytesAll(data, false);
    }

    /**
     * Write raw bytes in a byte array into the DataClient
     * 
     * @param dataBytes
     * @param compressible
     *            a flag indicating whether the data is compressible or not
     * @throws IllegalStateException
     *             if the DataClient is opened for read
     * @throws DataException
     *             if there is any I/O error when writing the data
     */
    public void writeRawBytesAll(byte[] dataBytes, boolean compressible)
            throws IllegalStateException, DataException {
        Utility.throwIfNull(dataBytes, "dataBytes");
        if (dataBytes.length == 0)
            throw new IllegalArgumentException(SR.v("ArgumentEmpty",
                    "dataBytes"));
        if (readonly)
            throw new DataException(DataErrorCode.DataClientReadOnly.getCode(),
                    SR.v("DataClientReadOnly"));

        if (!writable)
            throw new DataException(
                    DataErrorCode.DataClientNotWritable.getCode(), SR.v(
                            "DataClientNotWritable", this.id));

        File file = new File(pathToData);
        FileOutputStream originalStream = null;
        OutputStream output = null;
        try {
            originalStream = new FileOutputStream(file);
            if (compressible) {
                originalStream.write(compressionFlag.getBytes());
                originalStream.write(ToArray(dataBytes.length));
                output = new GZIPOutputStream(originalStream,
                        compressionBufferSize);
                output.write(dataBytes);
            } else {
                originalStream.write(dataBytes);
            }

            this.writable = false;
        } catch (FileNotFoundException e) {
            // FileNotFoundException may be thrown because of access denied.
            // so double check if file exists.
            if (file.exists())
                throw new AccessControlException(SR.v("DataNoPermission"));
            else
                throw new DataException(DataErrorCode.DataClientDeleted.getCode(),
                    SR.v("DataClientDeleted", this.id), e);
        } catch (IOException e) {
            throw new DataException(DataErrorCode.UnknownIOError.getCode(),
                    SR.v("DataUnknownIOError"), e);
        } finally {
            // to make sure "output" flushed data into "originalStream", make
            // sure output.close() is called before originalStream.close().
            try {
                if (output != null)
                    output.close();
            } catch (IOException e) {
                // ignore.
            }

            try {
                if (originalStream != null)
                    originalStream.close();
            } catch (IOException e) {
                // ignore.
            }
        }
    }

    /**
     * Get the path that refers to the underlying store of the data
     * 
     * @return the path that refers to the underlying store of the data
     */
    public String getStorePath() {
        return this.pathToData;
    }

    /**
     * Read the data
     * 
     * @return the data as a byte array
     */
    public byte[] readRawBytesAll() throws DataException {
        File file = new File(pathToData);
        if (!file.exists()) {
            throw new DataException(DataErrorCode.DataClientDeleted.getCode(),
                    SR.v("DataClientDeleted", this.id));
        }

        FileInputStream originalStream = null;
        InputStream input = null;
        try {
            byte[] content;
            long size;
            originalStream = new FileInputStream(file);

            // File.length() returns 0 if the file doesn't exist.
            // Calling File.length() after creating FileInputStream instance
            // makes sure that file exists
            long length = file.length();
            if (length == 0) {
                throw new DataException(
                        DataErrorCode.NoDataAvailable.getCode(), SR.v(
                                "NoDataAvailable", this.id));
            } else if (length >= compressionHeaderLen) {
                byte[] header = new byte[compressionFlag.length()];
                originalStream.read(header);
                boolean compress = Arrays.equals(header,
                        compressionFlag.getBytes());
                if (compress) {
                    byte[] sizeBytes = new byte[4];
                    originalStream.read(sizeBytes);
                    size = ToInt(sizeBytes);

                    input = new GZIPInputStream(originalStream);
                    content = new byte[(int) size];

                    int bytesRead = 0;
                    do {
                        int nBytes = input.read(content, bytesRead, (int) size
                                - bytesRead);
                        if (nBytes == -1) {
                            // eof
                            break;
                        }

                        bytesRead += nBytes;
                        if (bytesRead >= size) {
                            // all bytes returned. done
                            break;
                        }
                    } while (true);

                    if (bytesRead != size) {
                        throw new DataException(
                                DataErrorCode.DataInconsistent.getCode(), SR.v(
                                        "DataInconsistent", this.id));
                    }
                } else {
                    // not compress
                    size = length;
                    content = new byte[(int) size];
                    originalStream.read(content, compressionFlag.length(),
                            (int) size - compressionFlag.length());
                    System.arraycopy(header, 0, content, 0,
                            compressionFlag.length());
                }
            } else {
                content = new byte[(int) file.length()];
                originalStream.read(content);
            }

            return content;
        } catch (FileNotFoundException e) {
            // FileNotFoundException is thrown if client doesn't have read
            // permission to the data. so check if file exists on this exception
            if (file.exists()) {
                throw new AccessControlException(SR.v("DataNoPermission"));
            } else {
                throw new DataException(
                        DataErrorCode.DataClientDeleted.getCode(), SR.v(
                                "DataClientDeleted", this.id), e);
            }
        } catch (SecurityException e) {
            throw new SecurityException(SR.v("DataNoPermission"), e);
        } catch (IOException ioe) {
            throw new DataException(DataErrorCode.UnknownIOError.getCode(),
                    SR.v("DataUnknownIOError"), ioe);
        } finally {
            try {
                if (originalStream != null)
                    originalStream.close();
            } catch (IOException e) {
                // ignore
            }

            try {
                if (input != null)
                    input.close();
            } catch (IOException e) {
                // ignore.
            }
        }
    }

    /*
     * Close the client which is a noop now.
     */
    public void close() {
        ;
    }

    /**
     * Associate with session
     * 
     * @param sessionid
     * @throws SessionException
     */
    public void setDataLifeCycle(DataLifeCycle lifeCycle) throws DataException {
        if (readonly)
            throw new DataException(DataErrorCode.DataClientReadOnly.getCode(),
                    SR.v("DataClientReadOnly"));

        if (hasNamespace)
            throw new DataException(
                    DataErrorCode.DataClientLifeCycleSet.getCode(), SR.v(
                            "DataClientLifeCycleSet", this.id));

        SessionBasedDataLifeCycleContext context = (SessionBasedDataLifeCycleContext) lifeCycle.context;
        CxfDataServiceClient client = new CxfDataServiceClient(this.username,
                this.password, this.headnode);
        client.associateDataClientWithSession(this.id, context.sessionId);
        client.destory();

        hasNamespace = true;
    }

    /**
     * Create a new instance of DataClient
     * 
     * @param dataclientid
     * @param headnode
     * @param username
     * @param password
     * @return
     * @throws SessionException
     */
    public static DataClient create(String dataclientid, String headnode)
            throws DataException {
        return create(dataclientid, headnode, HpcJava.getUsername(),
                HpcJava.getPassword(), null);
    }

    /**
     * Create a new instance of DataClient
     * 
     * @param dataclientid
     * @param headnode
     * @param username
     * @param password
     * @return
     * @throws SessionException
     */
    public static DataClient create(String dataclientid, String headnode,
            String username, String password) throws DataException {
        return create(dataclientid, headnode, username, password, null);
    }

    /**
     * Create a new instance of DataClient
     * 
     * @param dataclientid
     * @param headnode
     * @param username
     * @param password
     * @return
     * @throws SessionException
     */
    public static DataClient create(String dataclientid, String headnode,
            String username, String password, String[] allowedUsers)
            throws DataException {
        Utility.throwIfNullOrEmpty(dataclientid, "dataclientid");
        Utility.throwIfTooLong(dataclientid.length(), "dataclientid", 128,
                SR.v("DataClientIdTooLong", 128));
        Utility.throwIfInvalidDataClientId(dataclientid,
                SR.v("DataClientIdInvalid"));
        Utility.throwIfNullOrEmpty(headnode, "headnode");
        Utility.throwIfNullOrEmpty(username, "username");
        Utility.throwIfNull(password, "password");

        try {
            CxfDataServiceClient client = new CxfDataServiceClient(username,
                    password, headnode);
            String dataPath = client.createDataClient(dataclientid, allowedUsers);
            client.destory();

            return new DataClient(dataPath, dataclientid, false, username,
                    password, headnode);
        } catch(DataException ex) {
            if(ex.getErrorCode() == DataErrorCode.DataNoPermission.getCode()) {
                throw new AccessControlException(ex.getMessage());
            } else {
                throw ex;
            }
        }
    }

    /**
     * Delete a data client
     * 
     * @param dataclientid
     * @param headnode
     * @param username
     * @param password
     * @throws SessionException
     */
    public static void delete(String dataclientid, String headnode,
            String username, String password) throws DataException {
        Utility.throwIfNullOrEmpty(dataclientid, "dataclientid");
        Utility.throwIfTooLong(dataclientid.length(), "dataclientid", 128,
                SR.v("DataClientIdTooLong"));
        Utility.throwIfInvalidDataClientId(dataclientid,
                SR.v("DataClientIdInvalid"));
        Utility.throwIfNullOrEmpty(headnode, "headnode");
        Utility.throwIfNullOrEmpty(username, "username");
        Utility.throwIfNull(password, "password");

        try {
            CxfDataServiceClient client = new CxfDataServiceClient(username,
                    password, headnode);
            client.deleteDataClient(dataclientid);
            client.destory();
        } catch(DataException ex) {
            if(ex.getErrorCode() == DataErrorCode.DataNoPermission.getCode()) {
                throw new AccessControlException(ex.getMessage());
            } else {
                throw ex;
            }
        }
    }

    /**
     * Open a data client
     * 
     * @param dataclientid
     * @param headnode
     * @param username
     * @param password
     * @return
     * @throws SessionException
     */
    public static DataClient open(String dataclientid, String headnode,
            String username, String password) throws DataException {
        Utility.throwIfNullOrEmpty(dataclientid, "dataclientid");
        Utility.throwIfTooLong(dataclientid.length(), "dataclientid", 128,
                SR.v("DataClientIdTooLong"));
        Utility.throwIfInvalidDataClientId(dataclientid,
                SR.v("DataClientIdInvalid"));
        Utility.throwIfNullOrEmpty(headnode, "headnode");
        Utility.throwIfNullOrEmpty(username, "username");
        Utility.throwIfNull(password, "password");

        try
        {
            CxfDataServiceClient client = new CxfDataServiceClient(username,
                    password, headnode);
            String dataPath = client.openDataClient(dataclientid);
            client.destory();
            
            return new DataClient(dataPath, dataclientid, true, username, password,
                    headnode);
        } catch(DataException ex) {
            if(ex.getErrorCode() == DataErrorCode.DataNoPermission.getCode()) {
                throw new AccessControlException(ex.getMessage());
            } else {
                throw ex;
            }
        }
    }

    /**
     * Open a data client on specified data server(file share)
     * @param dataserveraddress File share root path
     * @param dataclientid data client id
     * @return Data
     * @throws DataException
     */
    public static DataClient open(String dataserveraddress,
            String dataclientid) throws DataException {
        ValidateFileSharePath(dataserveraddress);
        String storeFilePath = GenerateDataClientPath(dataserveraddress, dataclientid);
        
        File file = new File(storeFilePath);
        if (!file.exists()) {
            throw new DataException(DataErrorCode.DataClientNotFound.getCode(),
                    SR.v("DataClientNotFound", dataclientid));
        }
        
        return new DataClient(storeFilePath, dataclientid, true, null, null, null);
    }
    
    /**
     * validate that file share path
     * 
     * @param path
     * @throws DataException
     *             if path is not a valid file share path
     */
    private static void ValidateFileSharePath(String path) throws DataException {
        // File share path should be in the format of: \\server\share..., and
        // server cannot be localhost
        Matcher m = fileSharePathPattern.matcher(path.trim());
        if (m.find() && m.groupCount() > 1) {
            String host = m.group(1);
            if (!host.equalsIgnoreCase(localHostName)) {
                return;
            }
        }

        throw new DataException(
                DataErrorCode.DataServerMisconfigured.getCode(), SR.v(
                        "InvalidFileShareFormat", path));
    }
    
    /**
     * generate store path for a data client
     * @param rootPath file share root path
     * @param dataClientId data client id
     * @return store path for the data client
     */
    private static String GenerateDataClientPath(String rootpath,
            String dataclientid) {
        String path = rootpath + File.separator +
        Integer.toString(getHashFromName(dataclientid)) + File.separator +
        dataclientid;
        
        return ConvertPath(path);
    }

    /**
     * get the hash code from the key
     * 
     * @param name
     * @return
     */
    private static int getHashFromName(String name) {
        byte[] values = name.getBytes();
        int i = 0;
        for (byte b : values) {
            i = i * 37 + b;
        }

        i = Math.abs(i);

        return i % 1024;
    }

    /**
     * convert an integer to 4 bytes
     * 
     * @param i
     * @return 4-byte representation of the integer
     */
    private static byte[] ToArray(int i) {
        byte[] bytes = new byte[4];
        for (int j = 4; j > 0; j--) {
            bytes[4 - j] = (byte) (i >>> 8 * (4 - j));
        }
        return bytes;
    }

    /**
     * convert a 4-byte array into an integer
     * 
     * @param bytes
     * @return integer value of the 4 bytes
     */
    private static int ToInt(byte[] bytes) {
        int ret = 0;
        for (int i = 0; i < 4; i++)
            ret = (ret << 8) | (bytes[3 - i] & 0xff);
        return ret;
    }
    
    private static String ConvertPath(String original)
    {
        if (System.getProperty("os.name").toLowerCase().indexOf("win") != -1)
            return original;
        else
        {
            // fetch the environment variable
            String runtimeShare = System.getenv("HPC_RUNTIMESHARE");
            
            if (runtimeShare == null)
                return original;
            
            int firstSlash = original.indexOf('\\', 3);
            int secondSlash = original.indexOf('\\', firstSlash + 1);
            
            if (secondSlash == -1)
            {
                return original;
            }
            
            String result = runtimeShare + original.substring(secondSlash);
            return result.replace('\\', '/');
        }
    }
}
