// Exercise1
package main

import (
	"fmt"
)

type dog struct {
	name   string
	race   string
	female bool
}

//Passes a pointer to the dog object and changes the name element to the target.
func (dawg *dog) rename(s string) {
	dawg.name = s
}

func main() {
	fido := dog{"Fido", "Poodle", false}
	fido.rename("Cocotte")

	fmt.Println(fido.name) // Verifies that the method worked.
}
