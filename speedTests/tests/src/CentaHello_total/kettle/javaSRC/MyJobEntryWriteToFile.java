package run;

import org.pentaho.di.job.JobMeta;
import org.pentaho.di.job.entries.writetofile.JobEntryWriteToFile;

public class MyJobEntryWriteToFile extends JobEntryWriteToFile {

    public MyJobEntryWriteToFile(
          String filename,
          boolean createParentFolder,
          boolean appendFile,
          String content) {
        super();
        setParentJobMeta(new JobMeta());
        setFilename(filename);
        setCreateParentFolder(createParentFolder);
        setAppendFile(appendFile);
        setContent(content);
    }

}
