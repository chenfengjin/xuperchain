package unmarshal

import (
	"fmt"
	"testing"
)
import "bytes"

func TestUnmarshal(t *testing.T) {
	bytebuf := bytes.NewBuffer([]byte{})
	//binary.Write(bytebuf, binary.BigEndian, data)
	a := map[string][]byte{
		"name": []byte("zhangsan"),
		"age":  bytebuf.Bytes(),
	}
	v := struct {
		Name string `json:"name"`
		//Age  int    `json:"age"`
	}{}
	Validate(a, &v)
	fmt.Println(v.Name)
}
