package main

import (
	"fmt"
	"spop/comms"
	readers "spop/readers/hop"
	writers "spop/writers/java"
)

type HopGenerater struct {
	inputPath  string
	outputPath string
	classes    chan comms.Class
	done       chan string
}

func (h HopGenerater) Exec(inputPath string, outputPath string) {
	h.inputPath = inputPath
	h.outputPath = outputPath
	h.classes = make(chan comms.Class)
	h.done = make(chan string)

	r := h.startReader()
	h.startWriter(r)
	h.wait()
}

func (h HopGenerater) startReader() readers.HopReader {
	r := readers.HopReader{h.inputPath, h.classes}
	go func() {
		defer close(h.classes)
		r.Exec()
		h.done <- "Reader finished"
	}()

	return r
}

func (h HopGenerater) startWriter(r readers.HopReader) {
	w := writers.JavaWriter{h.outputPath, h.classes, r}
	go func() {
		w.Exec()
		h.done <- "Writer finished"
	}()
}

func (h HopGenerater) wait() {
	for i := 0; i < 2; i++ {
		fmt.Println(<-h.done)
	}
}
