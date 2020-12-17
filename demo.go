package main

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"os"
)

func main(){
	f,_:=os.Open("a.json")
	s:= struct {
		Count int16 `json:"count"`
	}{}
	data,_:=ioutil.ReadAll(f)
	json.Unmarshal(data,&s);
	fmt.Println(s.Count)
}