package run;

import run.MyActionStart;
import run.MyActionWriteToFile;
import run.MyActionSuccess;
import run.MyActionWriteToFile;
import run.MyActionWriteToFile;
import run.MyActionWriteToFile;
import run.MyActionWriteToFile;
import run.MyActionWriteToFile;
import run.MyActionWriteToFile;
import run.MyActionWriteToFile;
import run.MyActionWriteToFile;
import run.MyActionWriteToFile;
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
        MyActionWriteToFile a3 = new MyActionWriteToFile(
                "../hello.txt",
                false,
                true,
                "Hello there\n");
        r = a3.execute(r,3);
        MyActionWriteToFile a4 = new MyActionWriteToFile(
                "../hello.txt",
                false,
                true,
                "Hello there\n");
        r = a4.execute(r,4);
        MyActionWriteToFile a5 = new MyActionWriteToFile(
                "../hello.txt",
                false,
                true,
                "Hello there\n");
        r = a5.execute(r,5);
        MyActionWriteToFile a6 = new MyActionWriteToFile(
                "../hello.txt",
                false,
                true,
                "Hello there\n");
        r = a6.execute(r,6);
        MyActionWriteToFile a7 = new MyActionWriteToFile(
                "../hello.txt",
                false,
                true,
                "Hello there\n");
        r = a7.execute(r,7);
        MyActionWriteToFile a8 = new MyActionWriteToFile(
                "../hello.txt",
                false,
                true,
                "Hello there\n");
        r = a8.execute(r,8);
        MyActionWriteToFile a9 = new MyActionWriteToFile(
                "../hello.txt",
                false,
                true,
                "Hello there\n");
        r = a9.execute(r,9);
        MyActionWriteToFile a10 = new MyActionWriteToFile(
                "../hello.txt",
                false,
                true,
                "Hello there\n");
        r = a10.execute(r,10);
        MyActionWriteToFile a11 = new MyActionWriteToFile(
                "../hello.txt",
                false,
                true,
                "Hello there\n");
        r = a11.execute(r,11);
    }

}
