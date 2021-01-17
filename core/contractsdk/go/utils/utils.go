package utils

import (
	"github.com/xuperchain/xuperchain/core/contractsdk/go/code"
	"github.com/xuperchain/xuperchain/core/xmodel"
	"unsafe"
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
