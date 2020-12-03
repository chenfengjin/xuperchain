package unmarshal

import (
	"errors"
	"fmt"
	"reflect"
)

//遍历struct并且自动进行赋值
func Validate(data map[string][]byte, inStructPtr interface{}) error {
	rType := reflect.TypeOf(inStructPtr)
	rVal := reflect.ValueOf(inStructPtr)
	if rType.Kind() == reflect.Ptr {
		// 传入的inStructPtr是指针，需要.Elem()取得指针指向的value
		rType = rType.Elem()
		rVal = rVal.Elem()
	} else {
		return errors.New("inStructPtr must be ptr to struct")
	}
	// 遍历结构体
	for i := 0; i < rType.NumField(); i++ { //TODO @fengjin 添加类型检查·
		t := rType.Field(i)
		f := rVal.Field(i)
		for k, v := range data {
			if t.Tag.Get("json") != k {
				continue
			}
			f.Set(reflect.ValueOf(v))
		}
		break
	}
	//检查所有 required 字段都 ok
	//考虑字符串为空白的情况
	for i := 0; i < rType.NumField(); i++ {
		t := rType.Field(i)
		f := rVal.Field(i)
		if required, ok := t.Tag.Lookup("required"); ok && required == "true" {
			if f.IsNil() {
				return fmt.Errorf("field %s is required", t.Name)
			}
		}
	}
	return nil
}

//}
