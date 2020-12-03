package main

import "fmt"

func demo(out *map[string]int) {
	for k, v := range *out {
		(*out)[k] = v + 1
	}
}
func main() {
	a := map[string]int{
		"a": 1,
		"b": 2,
	}
	demo(&a)
	fmt.Println(a)
}
