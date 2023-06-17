package hop

import (
	"spop/comms"
)

type HopReader struct {
	InputPath string
	Classes   chan<- comms.Class
}

func (h HopReader) Exec() {
	actions := HopXML(h.InputPath)
	for _, action := range actions {
		filteredAction := filter(action)
		if filteredAction.Name != "" {
			h.Classes <- filteredAction
		}
	}

}

func (h HopReader) GeneralClasses() []string {
	return []string{"org.apache.hop.core.Result",
		"org.apache.hop.core.exception.HopException"}
}

func (h HopReader) Exception() string {
	return "HopException"
}

func (h HopReader) ClassImports() []string {
	return []string{}
}

func (h HopReader) ClassSetup() []string {
	return []string{"super();"}
}
