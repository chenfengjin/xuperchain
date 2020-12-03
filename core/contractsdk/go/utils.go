package utils

import (
	"bytes"
	"errors"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/code"
	"github.com/xuperchain/xuperchain/core/xmodel"
	"unsafe"
)

var (
	ErrMissingCaller    = errors.New("missing caller")
	ErrPermissionDenied = errors.New("permission denied")
	ErrObjectExists     = errors.New("objet already exists")
)

const (
	LittleEndian = iota
	BigEndian
)

// error 不为空不代表存在
// TODO @fengjin check 下
func CheckExist(ctx code.Context, id []byte) error {
	_, err := ctx.GetObject(id)
	if err == nil {
		return ErrObjectExists
	}
	if err == xmodel.ErrNotFound {
		return nil
	}
	return err
}

//TODO 需要仔细确认下
func getByteOrder() int64 {
	a := uint64(1)
	b := (*[8]byte)(unsafe.Pointer(&a))
	//TODO @fengjin
	if b[0] == (1) {
		return 0

	} else {
		return 0
	}

}

//both a and b are byte representation of number str like []byte("1")
func Add(a []byte, b []byte) []byte {
	return nil
}

func Sub([]byte, []byte) []byte {
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
