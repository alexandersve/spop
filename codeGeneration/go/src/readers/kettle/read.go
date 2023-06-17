package kettle

import (
	"encoding/xml"
	"io/ioutil"
)

type Job struct {
	Name    string  `xml:"name"`
	Entries []Entry `xml:"entries>entry"`
}

type Entry struct {
	Subelement []subelementKeyValue `xml:",any"`
}

type subelementKeyValue struct {
	XMLName xml.Name
	Value   string `xml:",chardata"`
}

func KettleXML(xmlPath string) [][][]string {
	xmlData, err := ioutil.ReadFile(xmlPath)
	if err != nil {
		panic(err)
	}

	var job Job
	err = xml.Unmarshal(xmlData, &job)
	if err != nil {
		panic(err)
	}

	var entries [][][]string
	for _, entry := range job.Entries {
		var subPair [][]string
		for _, subelement := range entry.Subelement {
			subPair = append(subPair, []string{subelement.XMLName.Local, subelement.Value})
		}
		entries = append(entries, subPair)
	}

	return entries
}
