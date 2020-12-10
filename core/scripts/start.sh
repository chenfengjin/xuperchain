mkdir -p data/acl
echo "XC1111111111111111@xuper/dpzuVdosQrF2kmzumhVeFQZa1aYcdgFpN" > data/acl/addrs
xchain-cli account new --account 1111111111111111 --fee 2000000000000
xchain-cli transfer --to XC1111111111111111@xuper --amount 100000000000

cd contractsdk/evm/example/short_content && cp -r ../../dependency/* . && solc --bin --abi ShortContent.sol -o .

#for i in contractsdk/go/example/*;do echo building $i;GOOS=js GOARCH=wasm go build -mod=vendor github.com/xuperchain/xuperchain/core/$i ;done
#echo "build finished"
#xchain-cli wasm deploy --account XC1111111111111111@xuper --runtime go --cname award_manage -m -a '{"creator": "someone"}' -A data/acl/addrs -o tx.output --keys data/keys --name xuper -H localhost:37101 counter
#xchain-cli native deploy --account XC1111111111111111@xuper --fee 15587517 --runtime java contractsdk/java/example/counter/target/counter-0.1.0-jar-with-dependencies.jar --cname javacounter
