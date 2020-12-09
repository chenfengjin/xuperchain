package utils

import (
	"bytes"
	"encoding/binary"
	"errors"
	"fmt"
	"reflect"
	"strconv"
)

func byteToInt64(b []byte) int64 {
	bytebuff := bytes.NewBuffer(b)
	var data int64
	byteOrder := binary.LittleEndian
	if x {
		byteOrder = binary.BigEndian
	}
	binary.Read(bytebuff, binary.BigEndian, &data)
	return data
}

func Validate(data map[string][]byte, inStructPtr interface{}) error {
	rType := reflect.TypeOf(inStructPtr)
	rVal := reflect.ValueOf(inStructPtr)
	if rType.Kind() == reflect.Ptr {
		rType = rType.Elem()
		rVal = rVal.Elem()
	} else {
		return errors.New("inStructPtr must be ptr to struct")
	}

	for i := 0; i < rType.NumField(); i++ {
		t := rType.Field(i)
		f := rVal.Field(i)
		for k, v := range data {
			if t.Tag.Get("json") != k {
				continue
			}
			f.Set(reflect.ValueOf(v))

			ltStr, ok := t.Tag.Lookup("lt")
			if ok {
				value := byteToInt64(v)
				lt, err := strconv.ParseInt(ltStr, 10, 64)
				if err != nil {
					return err
				}
				if value >= lt {
					return fmt.Errorf("%s must be less than %s", t.Name, ltStr)
				}
			}
			gtStr, ok := t.Tag.Lookup(("gt"))
			if ok {
				value := byteToInt64(v)
				gt, err := strconv.ParseInt(gtStr, 10, 64)
				if err != nil {
					return err
				}
				if value <= gt {
					return fmt.Errorf("%s must be greater than %s", t.Name, ltStr)
				}
			}
			leStr, ok := t.Tag.Lookup("le")
			if ok {
				value := byteToInt64(v)
				le, err := strconv.ParseInt(leStr, 10, 64)
				if err != nil {
					return err
				}
				if value > le {
					return fmt.Errorf("%s must be less than or equal to %s", t.Name, ltStr)
				}
			}
			geStr, ok := t.Tag.Lookup("ge")
			if ok {
				value := byteToInt64(v)
				ge, err := strconv.ParseInt(geStr, 10, 64)
				if err != nil {
					return err
				}
				if value < ge {
					return errors.New("")
				}
			}
		}
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
