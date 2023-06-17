package run;

import org.pentaho.di.job.JobMeta;
import org.pentaho.di.job.entries.success.JobEntrySuccess;

public class MyJobEntrySuccess extends JobEntrySuccess {

    public MyJobEntrySuccess(
) {
        super();
        setParentJobMeta(new JobMeta());
    }

}
