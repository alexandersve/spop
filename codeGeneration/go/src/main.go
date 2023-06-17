package main

import (
	"fmt"
	"os"
)

type Generater interface {
	Exec(string, string)
}

type GenerateService struct {
	generater  Generater
	inputPath  string
	outputPath string
}

func main() {
	genServ, ok := GetComponents(GetFlags())

	if ok {
		genServ.generater.Exec(genServ.inputPath, genServ.outputPath)
	} else {
		os.Exit(1)
	}
}

func GetComponents(inputPath string, outputPath string, sourceType string, ok bool) (GenerateService, bool) {
	if !ok {
		return GenerateService{}, false
	}

	ok = true

	g := GenerateService{nil, inputPath, outputPath}
	switch sourceType {
	case "hop":
		g.generater = HopGenerater{}
	case "kettle":
		g.generater = KettleGenerater{}
	default:
		g.generater = nil
		ok = false
		fmt.Println("There exist no source type \"" + sourceType + "\"")
	}

	return g, ok
}
