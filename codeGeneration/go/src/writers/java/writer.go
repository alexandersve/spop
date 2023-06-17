package java

import (
	"fmt"
	"spop/comms"
)

type JavaWriter struct {
	OutputPath string
	Classes    <-chan comms.Class
	Reader     XMLReader
}

type XMLReader interface {
	GeneralClasses() []string
	Exception() string
	ClassImports() []string
	ClassSetup() []string
}

func (j JavaWriter) Exec() {
	c1, c2 := comms.FanOut(j.Classes)
	done := make(chan string)
	go func() {
		j.writeClasses(c1)
		done <- "Class writer finished"
	}()
	go func() {
		j.javaMain(c2)
		done <- "Main writer finished"
	}()

	for i := 0; i < 2; i++ {
		fmt.Println(<-done)
	}
}
