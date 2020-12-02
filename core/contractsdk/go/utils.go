package utils

import (
	"bytes"
	"errors"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/code"
	"github.com/xuperchain/xuperchain/core/xmodel"
)

var (
	ErrMissingCaller    = errors.New("missing caller")
	ErrPermissionDenied = errors.New("permission denied")
	ErrObjectExists     = errors.New("objet already exists")
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

//both a and b are byte representation of number str like []byte("1")
func Add(a []byte, b []byte) []byte {
	return nil
}

func Sub([]byte, []byte) []byte {
	return nil

}

func Compare([]byte, []byte) int {
	return 0

}

func ContactWithString(elems ...interface{}) []byte {
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
