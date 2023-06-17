package hop

import (
	"encoding/xml"
	"io/ioutil"
)

type Workflow struct {
	Name    string   `xml:"name"`
	Actions []Action `xml:"actions>action"`
}

type Action struct {
	Subelement []subelementKeyValue `xml:",any"`
}

type subelementKeyValue struct {
	XMLName xml.Name
	Value   string `xml:",chardata"`
}

func HopXML(xmlPath string) [][][]string {
	xmlData, err := ioutil.ReadFile(xmlPath)
	if err != nil {
		panic(err)
	}

	var workflow Workflow
	err = xml.Unmarshal(xmlData, &workflow)
	if err != nil {
		panic(err)
	}

	var actions [][][]string
	for _, action := range workflow.Actions {
		var subPair [][]string
		for _, subelement := range action.Subelement {
			subPair = append(subPair, []string{subelement.XMLName.Local, subelement.Value})
		}
		actions = append(actions, subPair)
	}

	return actions
}
