package main

import (
	"errors"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/code"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/driver"
	"github.com/xuperchain/xuperchain/core/contractsdk/go/utils"
)

const (
	TOPIC_LENGTH_LIMIT   = 36
	TITLE_LENGTH_LIMIT   = 100
	CONTENT_LENGTH_LIMIT = 3000
	USER_BUCKET          = "USER"
)

var (
	ErrContentLengthTooLong = errors.New("the length of topic or title or content is more than limitation")
)

type shortContent struct {
}

func (sc *shortContent) Initialize(ctx code.Context) code.Response {
	return code.OK(nil)
}

func (sc *shortContent) StoreShortContent(ctx code.Context) code.Response {
	args := struct {
		UserId  []byte `json:"user_id",required:"true"`
		Title   []byte `json:"title",required:"true"`
		Topic   []byte `json:"topic",required:"true"`
		Content []byte `json;"content",required:"true"`
	}{}
	if err := utils.Validate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	userKey := utils.ConcatWithString(USER_BUCKET, "/", args.UserId, "/", args.Topic, "/", args.Title)
	if len(args.Topic) > TOPIC_LENGTH_LIMIT || len(args.Title) > TITLE_LENGTH_LIMIT ||
		len(args.Content) > CONTENT_LENGTH_LIMIT {
		//	TODO 注意下这里 len 的含义
		return code.Error(ErrContentLengthTooLong)
	}
	if err := ctx.PutObject(userKey, args.Content); err != nil {
		return code.Error(err)
	}
	return code.OK(nil)
}

func (sc *shortContent) QueryByUser(ctx code.Context) code.Response {
	args := struct {
		UserID []byte `json:"user_id",required:"true"`
	}{}
	if err := utils.Validate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	start := utils.ConcatWithString(USER_BUCKET, "/", args.UserID, "/")
	end := utils.ConcatWithString(start, "~")
	result := []byte{}
	iter := ctx.NewIterator(start, end)
	defer iter.Close()
	//iter 需要关闭
	for iter.Next() {
		result = append(result, utils.ConcatWithString(iter.Key(), "\n", iter.Value(), "\n")...)
	}
	return code.OK(result)
}
func (sc *shortContent) QueryByTitle(ctx code.Context) code.Response {
	args := struct {
		UserId []byte `json:"user_id",required:"true"`
		Topic  []byte `json:"user_id",required:"true"`
		Title  []byte `json:"title",required:"true"`
	}{}
	if err := utils.Validate(ctx.Args(), &args); err != nil {
		return code.Error(err)
	}
	value, err := ctx.GetObject(utils.ConcatWithString(USER_BUCKET, "/", args.UserId, "/", args.Topic, "/", args.Title))
	if err != nil {
		return code.Error(err)
	}
	return code.OK(value)
}

func (sc *shortContent) QueryByTopic(ctx code.Context) code.Response {
	args := struct {
		UserId []byte `json:"user_id",required:"true"`
		Topic  []byte `json:"user_id",required:"true"`
	}{}
	start := utils.ConcatWithString(USER_BUCKET, "/", args.UserId, "/", args.Topic, "/")
	end := utils.ConcatWithString(start, "~")
	iter := ctx.NewIterator(start, end)
	defer iter.Close()
	result := []byte{}

	for iter.Next() {
		result = append(result, utils.ConcatWithString(iter.Key(), "\n", iter.Value(), "\n")...)
	}
	return code.OK(result)
}

func main() {
	driver.Serve(new(shortContent))
}
