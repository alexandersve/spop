package run;

//import run.RandomValueMeta;
//import run.TextFileOutputMeta;
//import org.pentaho.di.trans.steps.randomvalue.RandomValueMeta;
//import org.pentaho.di.trans.steps.textfileoutput.TextFileOutputMeta;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.step.StepMetaInterface;
import org.pentaho.di.trans.TransHopMeta;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.core.row.ValueMetaInterface;
import org.pentaho.di.core.row.value.ValueMetaString;

public class MyTransMeta extends TransMeta {

    public MyTransMeta() {
        super();
        init();
    }

    private void init() {
        //ValueMetaInterface[] fieldMeta = new ValueMetaInterface[1];
        //fieldMeta[0] = new ValueMetaString("Kettle");
        
        // Define a simple transformation with two steps: a "Random Value" step and a "Text File Output" step
        RandomValueMeta randomMeta = new RandomValueMeta();
        randomMeta.setDefault();
        randomMeta.allocate(3);
        randomMeta.setFieldName(new String[] { "Kettle"});
        randomMeta.setFieldType(new int[] { ValueMetaInterface.TYPE_STRING });

        TextFileOutputMeta outputMeta = new TextFileOutputMeta();
        outputMeta.setDefault();
        outputMeta.setFilename("../hello2");
        outputMeta.setHeaderEnabled(true);
        outputMeta.setFileFormat("DOS");
        outputMeta.setSeparator(";");
        outputMeta.setEnclosure("\"");
        outputMeta.setEnclosureForced(false);
        outputMeta.setEnclosureFixDisabled(false);
        outputMeta.setFooterEnabled(true);
        outputMeta.setFileCompression("None");
        outputMeta.setEncoding("windows-1252");
        outputMeta.setEndedLine("Now Hop: ");
        outputMeta.setFileNameInField(false);
        outputMeta.setCreateParentFolder(true);
        outputMeta.setDoNotOpenNewFileInit(false);
        outputMeta.setFileAppended(true);
        outputMeta.setSplitEvery(0);
        outputMeta.setAddToResultFiles(true);
        outputMeta.setPadded(false);
        outputMeta.setFastDump(false);
        //outputMeta.setFieldMeta(fieldMeta);

        // Define the connections between the steps
        StepMeta randomStep = new StepMeta("Random Value", randomMeta);
        StepMeta outputStep = new StepMeta("Text File Output", outputMeta);

        randomStep.setStepID("step1");
        outputStep.setStepID("step2");

        StepMetaInterface[] stepInterfaces = new StepMetaInterface[] { randomMeta, outputMeta };
        TransHopMeta hop = new TransHopMeta(randomStep, outputStep, true);

        // Define global settings
        setName("My Transformation");
        //setTransversion("1.0");
        // setStepInterfaces(stepInterfaces);
        addStep(randomStep);
        addStep(outputStep);
        addTransHop(hop);
        activateParameters(); //Needed?
    }
}

