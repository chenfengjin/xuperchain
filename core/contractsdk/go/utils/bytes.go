package utils

import "bytes"

//both a and b are byte representation of number str like []byte("1")
func Add(a, b interface{}) []byte {
	return nil
}

func Sub(a, b interface{}) []byte {
	return nil

}

//both a and b are int string byte slice
func Compare(a, b interface{}) int {
	//binary.LittleEndian.Uint16()
	return 0

}

func ConcatWithString(elems ...interface{}) []byte {
	buf := bytes.NewBuffer(nil)
	for _, elem := range elems {
		switch elem.(type) {
		case string:
			buf.WriteString(elem.(string))
		case []byte:
			buf.Write(elem.([]byte))
		default:
			return nil // TODO ok?
		}
	}
	return buf.Bytes()
}
