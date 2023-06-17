package comms

type Exit struct {
	Ok      bool
	Message string
}

type Class struct {
	Name     string
	Path     string
	Settings []Setting
}

type Setting struct {
	VarType string
	Name    string
	Value   string
}
