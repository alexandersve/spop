package run;

import run.MyTransformation;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.KettleEnvironment;

public class Main {

    public static void main(String[] args) throws KettleException {
        
        MyTransformation t = new MyTransformation();
        t.execute(args);



        // Wait for the transformation to finish
        t.waitUntilFinished();

        // Check for errors (Don't know if errors will show in my code)
        if (t.getErrors() > 0) {
            System.out.println("There were errors during transformation execution.");
        } else {
            //System.out.println("Transformation executed successfully.");
        }
    }

}
