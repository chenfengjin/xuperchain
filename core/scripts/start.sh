mkdir -p data/acl
echo "XC1111111111111111@xuper/dpzuVdosQrF2kmzumhVeFQZa1aYcdgFpN" > data/acl/addrs
./xchain-cli account new --account 1111111111111111 --fee 20000
pushd ../core/contractsdk/go/example/counter && GO111MODULE=on GOPROXY=https://goproxy.cn GOOS=js GOARCH=wasm go build && popd
echo "build finished"
./xchain-cli wasm deploy --account XC1111111111111111@xuper --cname counter -m -a '{"creator": "someone"}' -A data/acl/addrs -o tx.output --keys data/keys --name xuper -H localhost:37101 ../core/contractsdk/go/example/counter/counter



