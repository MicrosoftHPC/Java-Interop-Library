///////////////////////////////////////////////////////////////////////////////
//
// This file contains code which demonstrates the use of the Hpc Soam Wrapper
// within a service. 
//
///////////////////////////////////////////////////////////////////////////////
package sample.service;

import com.microsoft.hpc.soam.*;

import java.util.Date;
import sample.common.*;

public class MyService extends ServiceContainer {

    private ServiceContext m_serviceContext = null;
    private SessionContext m_sessionContext = null;

    /**
     *
     */
    public MyService() {
        super();
    }

    @Override
    public void onCreateService(ServiceContext serviceContext) throws SoamException {
        /**
         * ******************************************************************
         * Do your service initialization here.
         * ******************************************************************
         */
        m_serviceContext = serviceContext;
    }

    @Override
    public void onSessionEnter(SessionContext sessionContext) throws SoamException {
        /**
         * ******************************************************************
         * Do your session-specific initialization here, when common data is
         * provided.
         * ******************************************************************
         */
        m_sessionContext = sessionContext;
    }

    @Override
    public void onInvoke(TaskContext taskContext) throws SoamException {
        /**
         * ******************************************************************
         * Do your service logic here. This call applies to each task
         * submission.
         * ******************************************************************
         */

        // Get the input that was sent from the client 
        try {
            int factor = 10;
            
            byte[] commonData = m_sessionContext.getCommonData();
            if (commonData != null) {
                factor = commonData.length;
            }

            MyInput myInput = new MyInput();
            taskContext.populateTaskInput(myInput);

            // Set the output
            MyOutput myOutput = new MyOutput();
            myOutput.setBoolean(!myInput.isBoolean());
            myOutput.setInt(myInput.getInt() * factor);
            myOutput.setFloat(myInput.getFloat() * factor);
            myOutput.setDouble(myInput.getDouble() * factor);
            myOutput.setLong(myInput.getLong() * factor);
            myOutput.setString(m_serviceContext.getServiceName() + " returned: "
                    + myInput.getString().toUpperCase());
            myOutput.setBytes(commonData);
            myOutput.setDate(new Date());

            // Set our output message 
            taskContext.setTaskOutput(myOutput);
        } catch (Exception ex) {
            throw new SoamException(ex);
        }
    }

    @Override
    public void onSessionLeave() throws SoamException {
        /**
         * ******************************************************************
         * Do your session-specific uninitialization here, when common data is
         * provided.
         * ******************************************************************
         */
    }

    @Override
    public void onDestroyService() throws SoamException {
        /**
         * ******************************************************************
         * Do your service uninitialization here.
         * ******************************************************************
         */
    }

    @Override
    public void onApplicationAttach(ServiceContext serviceContext) throws SoamException {
    }

    @Override
    public void onApplicationDetach() throws SoamException {
    }

}
