package com.wso2.esb.log.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.AbstractSynapseHandler;
import org.apache.synapse.MessageContext;
import org.apache.synapse.SynapseLog;

import java.util.Set;

public class SESynapseLogHandler extends AbstractSynapseHandler {

    // Logger instance
    private static final Log LOGGER = LogFactory.getLog(SESynapseLogHandler.class);

    private static final String VALUE_SEPERATOR = " : ";
    private static final String PROPERTY_SEPERATOR = " , \n";


    /*
     * Incoming request to the service or API. This is the first entry point,
     * in fact it is after Axis2 layer. This is where we will determine the
     * tracking id and log HTTP method and headers similar to wire log.
     *
     * @see <a href=
     * "https://docs.wso2.com/display/ESB490/Writing+a+Synapse+Handler">
     * WSO2 Docs</a>
     */
    public boolean handleRequestInFlow(MessageContext synCtx) {
        return true;           // always return true
    }

    /*
     * Outgoing request from the service to the backend. This is where we will
     * log the outgoing HTTP address and headers.
     *
     * @see <a href=
     *    "https://docs.wso2.com/display/ESB490/Writing+a+Synapse+Handler">
     *    WSO2 Docs</a>
     */
    public boolean handleRequestOutFlow(MessageContext synCtx) {
        return true;           // always return true
    }

    /*
     * Incoming response from backend to service. This is where we will
     * log the backend response headers and status.
     *
     * @see <a href=
     * "https://docs.wso2.com/display/ESB490/Writing+a+Synapse+Handler">
     * WSO2 Docs</a>
     */
    public boolean handleResponseInFlow(MessageContext synCtx) {
        return true;           // always return true
    }

    /*
     * Outgoing response from the service to caller. This is where we will log
     * the service response header and status.
     *
     * @see <a href=
     * "https://docs.wso2.com/display/ESB490/Writing+a+Synapse+Handler">
     * WSO2 Docs</a>
     */
    public boolean handleResponseOutFlow(MessageContext mc) {

        SynapseLog log = SELogTrackUtil.getLog(mc, LOGGER);
        Set<String> propKeySet = (Set<String>) mc.getPropertyKeySet();
        StringBuilder sb = new StringBuilder("Synapse Properties : { ");
        for (String key: propKeySet) {
            sb.append(key);
            sb.append(VALUE_SEPERATOR);
            sb.append(mc.getProperty(key));
            sb.append(PROPERTY_SEPERATOR);
        }
        sb.append("}, ");

        try {
            // Set the logging context
            SELogTrackUtil.setLogContext(mc, log);
            log.auditLog(sb.toString());
        } catch (Exception e) {
            log.auditWarn("Unable to set log context due to : " + e.getMessage());
        }

        return true;           // always return true
    }
}
