package java

import (
	"os"
	"spop/comms"
	"spop/file"
	"strings"
)

func (j JavaWriter) writeClasses(classes <-chan comms.Class) {
	for action := range classes {
		file := file.New("My"+action.Name+".java", j.OutputPath)
		defer file.Close()
		j.classWrite(action, file)
	}
}

func (j JavaWriter) classWrite(action comms.Class, file *os.File) {
	// Sort input information
	classPath := action.Path
	className := action.Name
	classSettings := action.Settings

	// Generate code
	sIn := "    "

	file.WriteString("package run;\n\n")

	s := j.Reader.ClassImports()
	s = append(s, classPath)
	s1 := "import "
	s2 := ";\n"
	for i := 0; i < len(s); i++ {
		file.WriteString(s1 + s[i] + s2)
	}

	file.WriteString("\npublic class My" + className + " extends " + className + " {\n\n")

	file.WriteString(sIn + "public My" + className + "(\n")
	for i := 0; i < len(classSettings); i++ {
		if i == len(classSettings)-1 {
			file.WriteString(sIn + sIn + "  " + classSettings[i].VarType + " " + classSettings[i].Name)
		} else {
			file.WriteString(sIn + sIn + "  " + classSettings[i].VarType + " " + classSettings[i].Name + ",\n")
		}
	}
	file.WriteString(") {\n")
	for _, s := range j.Reader.ClassSetup() {
		file.WriteString(sIn + sIn + s + "\n")
	}
	for i := 0; i < len(classSettings); i++ {
		file.WriteString(sIn + sIn + "set" + strings.Title(classSettings[i].Name) + "(" + classSettings[i].Name + ");\n")
	}

	file.WriteString(sIn + "}\n\n")
	file.WriteString("}\n")
}
