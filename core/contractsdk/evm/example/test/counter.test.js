var assert = require("assert");

var codePath = "../build/Counter.bin"
var abiPath = "../build/Counter.abi"
var lang = "sol"
var type = "evm"

function deploy(totalSupply) {
    return xchain.Deploy({
        name: "Counter",
        code: codePath,
        lang: lang,
        type: type,
        abi: abiPath,
        init_args: { "owner": "admin" },
        options: { "account": "xchain9xchaingeinxchainoenguxchain" }
    });
}
function test(t) {
    c = deploy();
    console.log(deploy.address())
}

Test("demo", test);