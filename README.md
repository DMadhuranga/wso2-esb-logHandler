# wso2-esb-logHandler

wso2-esb-logHandler is sample handler which can used to log API transaction details in ESB.

Please follow the steps below to configure the given jar in your environment.

1) Download the Jar (wso2-esb-logHandler/target/wso2-esb-logHandler-1.0-SNAPSHOT.jar)
2) Copy Jar into <ESB-HOME>/repository/components/lib
3) Add the following configurations to <ESB_HOME>/repository/conf/synapse-handlers.xml file
```
<handlers>
     <handler name = "SESynapseLogHandler"class="com.wso2.esb.log.handler.SESynapseLogHandler"/>
</handlers>

```
4) Add the following configurations to <ESB_HOME>/repository/conf/log4j.properties
```
log4j.logger.com.wso2.esb.log.handler.SESynapseLogHandler=DEBUG
```
5) Start the server and create an API.
6) When you invoke the API, you will be able to see the logs successfully.
