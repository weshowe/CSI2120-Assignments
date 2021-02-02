/*
This file contains all four exercises. Corresponding methods are named e1, e2, etc.
Test scripts are loaded in main.
*/
package main

import (
	"errors"
	"fmt"
	"math"
	"strconv"
	"strings"
)

type Person struct {
	lastName  string
	firstName string
	iD        int
}

type course struct {
	NStudents int
	Professor string
	Avg       float64
}

func printPerson(p Person) {
	//Print formatted string as firstName lastName\nID: xxx
	fmt.Printf("Person: %s %s\nID: %d\n\n", p.firstName, p.lastName, p.iD)
}

// Takes a person object and adds the next ID to it.
func inPerson(p *Person, nextId int) (int, error) {

	var err error

	//Returns an error if the person object isn't initialized.
	if p == nil {
		err = errors.New("Attempting to Access Non-Existent Reference.")
	}

	p.iD = nextId

	return nextId + 1, err
}

//Exercise 1
func e1(x float64) int {

	return int(math.Floor(x) + math.Ceil(x))
}

//Exercise 2
func e2(lineWidth int, symb string) {
	lineSymb := ""

	for i := 0; i < lineWidth; i++ {
		lineSymb += symb // uses string concatenation to increase # of x's.
		formatStr := fmt.Sprintf("%%%ds\n", 2*lineWidth-i)
		fmt.Printf(formatStr, lineSymb)
	}

	for i := lineWidth; i > 0; i-- {
		lineSymb = lineSymb[0 : len(lineSymb)-1] // Slices out last x from string.
		formatStr := fmt.Sprintf("%%%ds\n", 2*lineWidth-i+2)
		fmt.Printf(formatStr, lineSymb)
	}
}

//Exercise3
func e3(arr [20]Person) {
	nextId := 101
	for i := 0; i < 20; i++ {
		var err error

		nextId, err = inPerson(&arr[i], nextId)
		if err != nil {
			fmt.Println("Invalid entry ... exiting")
			break
		}

		printPerson(arr[i])
	}
}

//Exercise4
func e4() {
	// Create a dynamic map m
	var m = make(map[string]course) // maps course codes to course structs.

	// Add the courses CSI2120 and CSI2110 to the map
	m["CSI2120"] = course{211, "Moura", 81.000000}
	m["CSI2110"] = course{186, "Lang", 79.500000}

	for k, v := range m {
		fmt.Printf("Course Code: %s\n", k)
		fmt.Printf("Number of students: %d\n", v.NStudents)
		fmt.Printf("Professor: %s\n", v.Professor)
		fmt.Printf("Average: %f\n\n", v.Avg)
	}
}

func main() {
	fmt.Println(e1(32.5)) // Function 1 takes predefined float
	e2(5, "x")            //Function 2 takes custom defined lineWidth and symbols.

	/* We build a test input array for the 3rd function before running it. */
	var personArray [20]Person

	for i := 0; i < len(personArray); i++ {
		personArray[i] = Person{strings.Join([]string{"Jingle", strconv.Itoa(i + 1)}, " "), "John", 0}
	}

	e3(personArray) // Function 3 takes the generated input array.

	e4() // Function 4 requires no input.

	// Have a nice day!
}
