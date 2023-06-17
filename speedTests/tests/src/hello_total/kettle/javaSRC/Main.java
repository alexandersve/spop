package run;

import run.MyJobEntrySpecial;
import run.MyJobEntryWriteToFile;
import run.MyJobEntrySuccess;
import org.pentaho.di.core.Result;
import org.pentaho.di.core.exception.KettleJobException;

public class Main {

    public static void main(String[] args) throws KettleJobException {

        Result r = new Result();
        MyJobEntrySpecial a0 = new MyJobEntrySpecial(
                true,
                false,
                false,
                0,
                0,
                60,
                12,
                0,
                1,
                1);
        r = a0.execute(r,0);
        MyJobEntryWriteToFile a1 = new MyJobEntryWriteToFile(
                "../hello.txt",
                false,
                true,
                "Hello there\n");
        r = a1.execute(r,1);
        MyJobEntrySuccess a2 = new MyJobEntrySuccess(
);
        r = a2.execute(r,2);
    }

}
