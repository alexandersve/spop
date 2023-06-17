package java

import (
	"os"
	"spop/file"
	"strconv"

	"spop/comms"
)

func (j JavaWriter) javaMain(c <-chan comms.Class) {
	// Sort input information
	classNames := []string{}
	classSettings := [][]string{}
	i := 0
	for action := range c {
		classNames = append(classNames, "My"+action.Name)
		classSettings = append(classSettings, []string{})
		for _, settings := range action.Settings {
			classSettings[i] = append(classSettings[i], settings.Value)
		}
		i++
	}

	file := file.New("Main.java", j.OutputPath)
	defer file.Close()

	j.mainWrite(file, classNames, classSettings)
}

func (j JavaWriter) mainWrite(file *os.File, classNames []string, classSettings [][]string) {

	sIn := "    "

	file.WriteString("package run;\n\n")

	s := []string{}
	for i := 0; i < len(classNames); i++ {
		s = append(s, "run."+classNames[i])
	}
	s = append(s, j.Reader.GeneralClasses()...)
	s1 := "import "
	s2 := ";\n"
	for i := 0; i < len(s); i++ {
		file.WriteString(s1 + s[i] + s2)
	}

	file.WriteString("\npublic class Main {\n\n")
	file.WriteString(sIn + "public static void main(String[] args) throws " + j.Reader.Exception() + " {\n\n")

	file.WriteString(sIn + sIn + "Result r = new Result();\n")
	for i := 0; i < len(classNames); i++ {
		file.WriteString(sIn + sIn + classNames[i] + " a" + strconv.Itoa(i) + " = new " + classNames[i] + "(\n")
		for j := 0; j < len(classSettings[i]); j++ {
			if j != (len(classSettings[i]) - 1) {
				file.WriteString(sIn + sIn + sIn + sIn + classSettings[i][j] + ",\n")
			} else {
				file.WriteString(sIn + sIn + sIn + sIn + classSettings[i][j])
			}
		}
		file.WriteString(");\n")
		file.WriteString(sIn + sIn + "r = a" + strconv.Itoa(i) + ".execute(r," + strconv.Itoa(i) + ");\n")
	}

	file.WriteString(sIn + "}\n\n")
	file.WriteString("}\n")
}
