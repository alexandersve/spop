package main

import (
	"flag"
	"fmt"
)

var nrFlags = 3

type privFlag struct {
	name     string
	flagname string
	value    *string
	info     string
	useMsg   string
	errMsg   string
}

func GetFlags() (string, string, string, bool) {
	flags := allFlags()
	for _, f := range flags {
		flag.StringVar(f.value, f.flagname, *f.value, f.info)
	}
	flag.Parse()
	ok := flagsOk(flags)

	if len(flags) != nrFlags {
		fmt.Println("Not the expected amount of flags defined in spop/flags.go")
		ok = false
	}

	return *flags[0].value, *flags[1].value, *flags[2].value, ok
}

func flagsOk(flags []privFlag) bool {
	ok := true

	for _, f := range flags {
		if *f.value == "" {
			ok = false
		}
	}

	if ok != true {
		incorrectFlags(flags)
	}

	return ok
}

func incorrectFlags(flags []privFlag) {
	fmt.Println("Incorrect flags\n")
	for _, f := range flags {
		if *f.value == "" {
			fmt.Println(f.errMsg)
		}
	}
	fmt.Println("")
	fmt.Println("Usage:")
	for _, f := range allFlags() {
		fmt.Println(f.useMsg)
	}
}

func allFlags() []privFlag {
	flags := []privFlag{}
	flags = append(flags, privFlag{
		"inputPath",
		"i",
		new(string),
		"Path to the input file",
		"-i \"inputPath\" must be set",
		"-i, inputPath not set"},
	)
	flags = append(flags, privFlag{
		"outputPath",
		"o",
		new(string),
		"Path to the output file",
		"-o \"outputPath\" must be set",
		"-o, outputPath not set"},
	)
	flags = append(flags, privFlag{
		"type",
		"t",
		new(string),
		"Hop or Kettle",
		"-t \"type\" must be set",
		"-t, type not set"},
	)

	return flags
}
