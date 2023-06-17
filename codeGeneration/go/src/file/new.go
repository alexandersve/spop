package file

import (
	"os"
	"path/filepath"
)

func New(fileName string, dirPath string) *os.File {
	destFile := filepath.Join(dirPath, fileName)
	file, err := os.Create(destFile)
	if err != nil {
		panic(err)
	}
	return file
}
