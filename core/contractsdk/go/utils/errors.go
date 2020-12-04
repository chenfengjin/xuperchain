package utils

import "errors"

var (
	ErrMissingCaller    = errors.New("missing caller")
	ErrPermissionDenied = errors.New("permission denied")
	ErrObjectExists     = errors.New("objet already exists")
	ErrBalanceLow       = errors.New("balance not enough") // TODO check 下语法
)
