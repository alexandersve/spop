package main

import (
	"fmt"
	"spop/comms"
	readers "spop/readers/kettle"
	writers "spop/writers/java"
)

type KettleGenerater struct {
	inputPath  string
	outputPath string
	classes    chan comms.Class
	done       chan string
}

func (k KettleGenerater) Exec(inputPath string, outputPath string) {
	k.inputPath = inputPath
	k.outputPath = outputPath
	k.classes = make(chan comms.Class)
	k.done = make(chan string)

	r := k.startReader()
	k.startWriter(r)
	k.wait()
}

func (k KettleGenerater) startReader() readers.KettleReader {
	r := readers.KettleReader{k.inputPath, k.classes}
	go func() {
		defer close(k.classes)
		r.Exec()
		k.done <- "Reader finished"
	}()

	return r
}

func (k KettleGenerater) startWriter(r readers.KettleReader) {
	w := writers.JavaWriter{k.outputPath, k.classes, r}
	go func() {
		w.Exec()
		k.done <- "Writer finished"
	}()
}

func (k KettleGenerater) wait() {
	for i := 0; i < 2; i++ {
		fmt.Println(<-k.done)
	}
}
