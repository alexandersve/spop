package run;

import org.pentaho.di.core.logging.LogChannelInterface;
import org.pentaho.di.core.logging.LogLevel;
import org.pentaho.di.core.logging.MetricsInterface;

public class EmptyLogChannel implements LogChannelInterface {

    private String logId;
    private String objectId;
    private LogLevel ll;
    private String filter;
    private boolean sepLog;
    private boolean gatMet;

    public EmptyLogChannel() {
        logId = "999";
        filter = "";
        sepLog = false;
        gatMet = false;
    }

    /**
     * @return the id of the logging channel
     */
    public String getLogChannelId() {
        return logId;
    }

    public LogLevel getLogLevel() {
        return ll;
    }

    public void setLogLevel( LogLevel logLevel ) {
        ll = logLevel;
    }

    /**
    * @return the containerObjectId
    */
    public String getContainerObjectId() {
        return objectId;
    }

    /**
    * @param containerObjectId
    *          the containerObjectId to set
    */
    public void setContainerObjectId( String containerObjectId ) {
        objectId = containerObjectId;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter( String filter ) {
        this.filter = filter;
    }

    public boolean isBasic() {return true;}

    public boolean isDetailed() {return false;}

    public boolean isDebug() {return false;}

    public boolean isRowLevel() {return false;}

    public boolean isError() {return false;}

    public void logMinimal( String message ) {
    }

    public void logMinimal( String message, Object... arguments ) {
        logMinimal(message);
        for (int i = 0; i < arguments.length; i++) {
            logMinimal("Argument " + i + ": " + arguments[i]);
        }
    }

    public void logBasic( String message ) {
        logMinimal(message);
    }

    public void logBasic( String message, Object... arguments ) {
        logMinimal(message);
        for (int i = 0; i < arguments.length; i++) {
            logMinimal("Argument " + i + ": " + arguments[i]);
        }
    }

    public void logDetailed( String message ) {
        logMinimal(message);
    }

    public void logDetailed( String message, Object... arguments ) {
        logMinimal(message);
        for (int i = 0; i < arguments.length; i++) {
            logMinimal("Argument " + i + ": " + arguments[i]);
        }
    }

    public void logDebug( String message ) {
        logMinimal(message);
    }

    public void logDebug( String message, Object... arguments ) {
        logMinimal(message);
        for (int i = 0; i < arguments.length; i++) {
            logMinimal("Argument " + i + ": " + arguments[i]);
        }
    }

    public void logRowlevel( String message ) {
        logMinimal(message);
    }

    public void logRowlevel( String message, Object... arguments ) {
        logMinimal(message);
        for (int i = 0; i < arguments.length; i++) {
            logMinimal("Argument " + i + ": " + arguments[i]);
        }
    }

    public void logError( String message ) {
        logMinimal(message);
    }

    public void logError( String message, Throwable e ) {
        logMinimal(message);
        logMinimal(e.toString());
    }

    public void logError( String message, Object... arguments ) {
        logMinimal(message);
        for (int i = 0; i < arguments.length; i++) {
            logMinimal("Argument " + i + ": " + arguments[i]);
        }
    }

    public boolean isGatheringMetrics() {
        return gatMet;
    }

    public void setGatheringMetrics( boolean gatheringMetrics ) {
        gatMet = gatheringMetrics;
    }

    /**
    * This option will force the create of a separate logging channel even if the logging concerns identical objects with
    * identical names.
    *
    * @param forcingSeparateLogging
    *          Set to true to force separate logging
    */
    public void setForcingSeparateLogging( boolean forcingSeparateLogging ) {
        sepLog = forcingSeparateLogging;
    }

    /**
    * @return True if the logging is forcibly separated out from even identical objects.
    */
    public boolean isForcingSeparateLogging() {
        return sepLog;
    }

    /**
    * Add a snapshot to the metrics system for this log channel at the time of invocation. This will process the value
    * depending on the type of metric specified. For example, for MetricsInterface.look up the maximum value in the
    * metrics and replace it if the new value is higher. The snapshot date will be retained in that case.
    *
    * @param metric
    *          The metric to use (ex. connect to a database)
    * @param value
    *          the value to store
    */
    public void snap( MetricsInterface metric, long... value ) {
    }

    /**
    * Add a maximum snapshot to the metrics system for this log channel at the time of invocation. This will look up the
    * maximum value in the metrics and replace it if the new value is higher. The snapshot date will be retained in that
    * case.
    *
    * @param metric
    *          The metric to use (ex. connect to a database)
    * @param subject
    *          The subject (ex. the name of the database)
    * @param value
    *          the value to store
    */
    public void snap( MetricsInterface metric, String subject, long... value ) {
    }

}
