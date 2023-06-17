package run;

import run.MyJobEntrySpecial;
import run.MyJobEntryWriteToFile;
import run.MyJobEntrySuccess;
import run.MyJobEntryWriteToFile;
import run.MyJobEntryWriteToFile;
import run.MyJobEntryWriteToFile;
import run.MyJobEntryWriteToFile;
import run.MyJobEntryWriteToFile;
import run.MyJobEntryWriteToFile;
import run.MyJobEntryWriteToFile;
import run.MyJobEntryWriteToFile;
import run.MyJobEntryWriteToFile;
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
        MyJobEntryWriteToFile a3 = new MyJobEntryWriteToFile(
                "../hello.txt",
                false,
                true,
                "Hello there\n");
        r = a3.execute(r,3);
        MyJobEntryWriteToFile a4 = new MyJobEntryWriteToFile(
                "../hello.txt",
                false,
                true,
                "Hello there\n");
        r = a4.execute(r,4);
        MyJobEntryWriteToFile a5 = new MyJobEntryWriteToFile(
                "../hello.txt",
                false,
                true,
                "Hello there\n");
        r = a5.execute(r,5);
        MyJobEntryWriteToFile a6 = new MyJobEntryWriteToFile(
                "../hello.txt",
                false,
                true,
                "Hello there\n");
        r = a6.execute(r,6);
        MyJobEntryWriteToFile a7 = new MyJobEntryWriteToFile(
                "../hello.txt",
                false,
                true,
                "Hello there\n");
        r = a7.execute(r,7);
        MyJobEntryWriteToFile a8 = new MyJobEntryWriteToFile(
                "../hello.txt",
                false,
                true,
                "Hello there\n");
        r = a8.execute(r,8);
        MyJobEntryWriteToFile a9 = new MyJobEntryWriteToFile(
                "../hello.txt",
                false,
                true,
                "Hello there\n");
        r = a9.execute(r,9);
        MyJobEntryWriteToFile a10 = new MyJobEntryWriteToFile(
                "../hello.txt",
                false,
                true,
                "Hello there\n");
        r = a10.execute(r,10);
        MyJobEntryWriteToFile a11 = new MyJobEntryWriteToFile(
                "../hello.txt",
                false,
                true,
                "Hello there\n");
        r = a11.execute(r,11);
    }

}
