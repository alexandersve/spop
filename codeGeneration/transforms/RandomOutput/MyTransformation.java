package run;

import org.pentaho.di.trans.Trans;
import org.pentaho.di.core.exception.KettleException;

public class MyTransformation extends Trans {

    public MyTransformation() {
        super(new MyTransMeta());

    }

    @Override
    public void execute(String[] arguments) throws KettleException {
        log = new MyLogChannel();
        super.prepareExecution( arguments );
        startThreads();
    }

}
