all: build

.PHONY: all test clean
export GO111MODULE=on

export XCHAIN_ROOT := ${PWD}/output
PATH := $(PATH):(XCHAIN_ROOT)/bin
#  生成所有需要的文件并且
build-debug:wabt
	bash core/scripts/build-debug.sh
	mv core/xvm/compile/wabt/build/wasm2c $(XCHAIN_ROOT)/bin


build-release:wabt
	bash core/scripts/build.sh
	mv core/xvm/compile/wabt/build/wasm2c $(XCHAIN_ROOT)/bin


wabt:
	make -C core/xvm/compile/wabt -j 8

build:build-release contractsdk

install:
	echo set env to xchain
	cd $(XCHAIN_ROOT) &&  xchain-cli createChain
	cd $(XCHAIN_ROOT)&& ./xchain &  && xchain-cli account new --account 1111111111111111 --fee 2000000000000 && xchain-cli transfer --to XC1111111111111111@xuper --amount 100000000000000
	echo TBD
test:contractsdk-test
	go test -coverprofile=coverage.txt -covermode=atomic ./...
	make -C 
	# GOOS=js GOARCH=wasm go build github.com/xuperchain/xuperchain/core/contractsdk/go/driver 这个测试测的啥

clean:
	rm -rf core/xvm/compile/wabt/build
	rm -rf output

contractsdk:
	#make -C core/contractsdk build
	echo TBD

contractsdk-test:contractsdk
	make -C core/contractsdk test

contract:
	docker build -t xuper/xuperchain-local . && docker run -it --name xchain --rm xuper/xuperchain-dev && docker exec -it xchain bash ../core/scripts/start.sh 
#  build by docker and output to local storage
docker-build:

docker-test:
#  build docker image 
build-image:
	#
