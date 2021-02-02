/**
* Well, I didn't have to do anything, but this was a fun little demonstration
* of how channels work. Making i global and modifying it with both the methods
* and the loop was a little odd. The use of time.Sleep to space out console output
* was a good idea - I've often done this. 10/10, would make and deliver
* fake Strawberry cakes again.
 */
package main

import (
	"fmt"
	"strconv"
	"time"
)

var i int

func makeCakeAndSend(cs chan string) {
	i = i + 1
	cakeName := "Strawberry Cake " + strconv.Itoa(i)
	fmt.Println("Making a cake and sending ...", cakeName)
	cs <- cakeName //send a strawberry cake
}

func receiveCakeAndPack(cs chan string) {
	s := <-cs //get whatever cake is on the channel
	fmt.Println("Packing received cake: ", s)
}

func main() {

	cs := make(chan string)

	for i := 0; i < 3; i++ {
		go makeCakeAndSend(cs)
		go receiveCakeAndPack(cs)

		//sleep for a while so that the program doesn't exit
		//immediately and output is clear for illustration
		time.Sleep(1 * 1e9)
	}
}
