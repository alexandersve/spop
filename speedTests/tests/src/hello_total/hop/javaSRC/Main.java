package run;

import run.MyActionStart;
import run.MyActionWriteToFile;
import run.MyActionSuccess;
import org.apache.hop.core.Result;
import org.apache.hop.core.exception.HopException;

public class Main {

    public static void main(String[] args) throws HopException {

        Result r = new Result();
        MyActionStart a0 = new MyActionStart(
                1,
                12,
                60,
                0,
                0,
                false,
                0,
                1);
        r = a0.execute(r,0);
        MyActionWriteToFile a1 = new MyActionWriteToFile(
                "../hello.txt",
                false,
                true,
                "Hello there\n");
        r = a1.execute(r,1);
        MyActionSuccess a2 = new MyActionSuccess(
);
        r = a2.execute(r,2);
    }

}
