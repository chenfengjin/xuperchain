all: build
export GO111MODULE=on
export GOFLAGS=-mod=vendor
XCHAIN_ROOT := ${PWD}/core
export XCHAIN_ROOT
PATH := ${PWD}/core/xvm/compile/wabt/build:$(PATH)

build:
	./core/scripts/build.sh

test:
	go test -coverprofile=coverage.txt -covermode=atomic ./...
	# test wasm sdk
	GOOS=js GOARCH=wasm go build github.com/xuperchain/xuperchain/core/contractsdk/go/driver

contractsdk:
	make -C core/contractsdk/cpp build
	make -C core/contractsdk/cpp test

contract:
	docker build -t xuper/xuperchain-local . && docker run -it --name xchain --rm xuper/xuperchain-dev && docker exec -it xchain bash ../core/scripts/start.sh 

clean:
	rm -rf output
	rm -f xchain-cli
	rm -f xchain
	rm -f dump_chain
	rm -f event_client
	rm -rf core/xvm/compile/wabt/build
	find . -name '*.so.*' -exec rm {} \;

.PHONY: all test clean
