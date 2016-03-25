///////////////////////////////////////////////////////////////////////////////
//
// This file is a part of the "SampleApp" project which implements all the
// common classes shared by the client and service samples.
//
///////////////////////////////////////////////////////////////////////////////
package sample.common;

import com.microsoft.hpc.soam.Message;
import com.microsoft.hpc.soam.SoamException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.codehaus.jackson.*;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.*;

/////////////////////////////////
// Input Data Object
/////////////////////////////////
public class MyInput extends Message {
    //=========================================================================
    //  Constructors
    //=========================================================================

    public MyInput() {
        super();
    }

    //=========================================================================
    //  Accessors and Mutators
    //=========================================================================
    public boolean isBoolean() {
        return m_boolean;
    }

    public void setBoolean(boolean m_boolean) {
        this.m_boolean = m_boolean;
    }

    public int getInt() {
        return m_int;
    }

    public void setInt(int m_int) {
        this.m_int = m_int;
    }

    public long getLong() {
        return m_long;
    }

    public void setLong(long m_long) {
        this.m_long = m_long;
    }

    public float getFloat() {
        return m_float;
    }

    public void setFloat(float m_float) {
        this.m_float = m_float;
    }

    public double getDouble() {
        return m_double;
    }

    public void setDouble(double m_double) {
        this.m_double = m_double;
    }

    public String getString() {
        return m_string;
    }

    public void setString(String m_string) {
        this.m_string = m_string;
    }

    public byte[] getBytes() {
        return m_bytes;
    }

    public void setBytes(byte[] m_bytes) {
        this.m_bytes = m_bytes;
    }

    public Date getDate() {
        return m_date;
    }

    public void setDate(Date m_date) {
        this.m_date = m_date;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("bool = ").append(this.m_boolean).append(", ");
        sb.append("int = ").append(this.m_int).append(", ");
        sb.append("long = ").append(this.m_long).append(", ");
        sb.append("float = ").append(this.m_float).append(", ");
        sb.append("double = ").append(this.m_double).append(", ");
        sb.append("string = ").append(this.m_string).append(", ");
        sb.append("bytes size = ").append((this.m_bytes==null)?0:this.m_bytes.length).append(", ");
        sb.append("date = ").append(this.m_date.toString()).append(", ");

        return sb.toString();
    }

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(dateFormat);
    }

    //=========================================================================
    //  Private Member Variables
    //=========================================================================
    @JsonProperty("m_boolean")
    private boolean m_boolean = false;
    @JsonProperty("m_int")
    private int m_int = 123;
    @JsonProperty("m_long")
    private long m_long = 123456L;
    @JsonProperty("m_float")
    private float m_float = 123.456F;
    @JsonProperty("m_double")
    private double m_double = 123.456789;
    @JsonProperty("m_string")
    private String m_string = "This is a sample string from MyInput.";
    @JsonProperty("m_bytes")
    private byte[] m_bytes = {0x11, 0x22, 0x33, 0x44};
    @JsonProperty("m_date")
    private Date m_date = new Date();

    @Override
    public void onDeserialize(InputStream stream) throws SoamException {
        try {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] data = new byte[4096];
            int count = -1;
            while ((count = stream.read(data, 0, 4096)) != -1) {
                outStream.write(data, 0, count);
            }
            data = null;
            String json = new String(outStream.toByteArray(), "ISO-8859-1");
            MyInput my = MyInput.fromJson(json);

            this.m_boolean = my.isBoolean();
            this.m_int = my.getInt();
            this.m_long = my.getLong();
            this.m_float = my.getFloat();
            this.m_double = my.getDouble();
            this.m_string = my.getString();
            this.m_bytes = my.getBytes();
            this.m_date = my.getDate();
        } catch (Exception ex) {
            throw new SoamException(ex);
        }
    }

    @Override
    public void onSerialize(OutputStream stream) throws SoamException {
        try {
            String json = this.toJson();
            stream.write(json.getBytes());
        } catch (Exception ex) {
            throw new SoamException(ex);
        }
    }

    public String toJson() throws JsonGenerationException, JsonMappingException, IOException {
        return mapper.writeValueAsString(this);
    }

    public static MyInput fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
        if (json == null) {
            return null;
        } else {
            return mapper.readValue(json, MyInput.class);
        }
    }

}
