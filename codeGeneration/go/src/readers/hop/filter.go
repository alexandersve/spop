package hop

import (
	"bytes"
	"fmt"
	"os"
	"os/exec"
	"path/filepath"
	"strconv"
	"strings"
	"unicode"

	"spop/comms"
)

func filter(action [][]string) comms.Class {

	//Filter
	var filteredAction comms.Class
	for _, subPair := range action {
		add := true
		var className string

		switch subPair[0] {
		case "name", "parallel", "xloc", "yloc":
			add = false
		case "type":
			if subPair[1] == "SPECIAL" {
				subPair[1] = "START"
			}
			className = subPair[1]
			subPair[0] = "class"
			subPair[1] = ""
		}

		if checkAllDigits(subPair[1]) {
			subPair = append([]string{"int"}, subPair...)
		} else {
			switch subPair[1] {
			case "":
				add = false
			case "Y", "N":
				subPair[1] = strconv.FormatBool(subPair[1] == "Y")
				subPair = append([]string{"boolean"}, subPair...)
			default:
				subPair = append([]string{"String"}, subPair...)
				subPair[2] = fixString(subPair[2])
			}
		}
		if add {
			var setting comms.Setting
			setting.VarType = subPair[0]
			setting.Name = subPair[1]
			setting.Value = subPair[2]
			filteredAction.Settings = append(filteredAction.Settings, setting)
		}
		if className != "" {
			className = getClassName(className)
			pathName := getPathName(className)
			filteredAction.Name = className
			filteredAction.Path = pathName
		}
	}
	return filteredAction
}

func getClassName(className string) string {
	words := strings.Split(className, "_")
	for i, word := range words {
		runes := []rune(word)
		for j := range runes {
			runes[j] = unicode.ToLower(runes[j])
		}
		runes[0] = unicode.ToUpper(runes[0])
		words[i] = string(runes)
	}
	return "Action" + strings.Join(words, "")
}

func getPathName(className string) string {
	// Create a new command to execute the Bash script
	executable, err1 := os.Executable()
	if err1 != nil {
		panic(err1)
	}
	execDir := filepath.Dir(executable)
	scriptsDir := filepath.Join(execDir, "..")
	cmd := exec.Command("bash", scriptsDir+"/findClassPathHop.sh", "-c", className, "hop")

	// Create a buffer to capture the output of the Bash script
	var output bytes.Buffer
	cmd.Stdout = &output

	// Execute the command and wait for it to complete
	err2 := cmd.Run()
	if err2 != nil {
		fmt.Println("Error:", err2)
		return ""
	}

	// Return the output of the Bash script
	pathName := output.String()

	words := strings.Split(pathName, "/")

	// Remove .class ending
	pathName = strings.Join(words, ".")
	words = strings.Split(pathName, ".")
	words = words[:len(words)-1]
	pathName = strings.Join(words, ".")

	return pathName
}

func fixString(s string) string {
	s = strings.ReplaceAll(s, "\n", "\\n")

	isNumber := true
	for _, c := range s {
		if !unicode.IsDigit(c) {
			isNumber = false
		}
	}

	if isNumber {
		return s
	} else {
		return "\"" + s + "\""
	}
}

func checkAllDigits(s string) bool {
	if len(s) == 0 {
		return false
	}
	for _, c := range s {
		if !unicode.IsDigit(c) {
			return false
		}
	}
	return true
}
