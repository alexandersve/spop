package run;

import org.apache.hop.workflow.actions.writetofile.ActionWriteToFile;

public class MyActionWriteToFile extends ActionWriteToFile {

    public MyActionWriteToFile(
          String filename,
          boolean createParentFolder,
          boolean appendFile,
          String content) {
        super();
        setFilename(filename);
        setCreateParentFolder(createParentFolder);
        setAppendFile(appendFile);
        setContent(content);
    }

}
