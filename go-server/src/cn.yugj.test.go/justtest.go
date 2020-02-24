package main

import (
	"strings"
	"strconv"
	"fmt"
)

func main() {
	//basic()
	point()
}

func point() {
	//test
	var i1 = 5
	fmt.Printf("An integer: %d, it's location in memory: %p\n", i1, &i1)

	var intP *int
	intP = &i1
	println("intP val:", *intP)
}

func basic() {
	hell := "aa,a,aaa"
	var sp = strings.Split(hell, ",")
	var flag = strings.HasPrefix(hell, "a")
	println(flag)

	var sj = strings.Join(sp, ";")
	println(sj)

	hellint := "4"
	var hellconv, _ = strconv.Atoi(hellint)
	println(hellconv)
	println(hellint + "5")
}
