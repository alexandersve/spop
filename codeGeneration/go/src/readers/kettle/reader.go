package kettle

import (
	"spop/comms"
)

type KettleReader struct {
	InputPath string
	Classes   chan<- comms.Class
}

func (k KettleReader) Exec() {
	actions := KettleXML(k.InputPath)
	for _, action := range actions {
		filteredAction := filter(action)
		if filteredAction.Name != "" {
			k.Classes <- filteredAction
		}
	}

}

func (k KettleReader) GeneralClasses() []string {
	return []string{"org.pentaho.di.core.Result",
		"org.pentaho.di.core.exception.KettleJobException"}
}

func (k KettleReader) Exception() string {
	return "KettleJobException"
}

func (k KettleReader) ClassImports() []string {
	return []string{"org.pentaho.di.job.JobMeta"}
}

func (k KettleReader) ClassSetup() []string {
	return []string{"super();",
		"setParentJobMeta(new JobMeta());"}
}
