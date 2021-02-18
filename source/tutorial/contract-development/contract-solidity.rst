
.. _tutorial/contract-development-solidity:

Solidity合约
^^^^^^^^^^^^

预备知识
>>>>>>>>

:ref:`tutorial/cli`

.. note::

    请先完成 :ref:`tutorial/cli`  中的教程，以确设置对应账号和权限
    

1.编译环境准备
>>>>>>>>>>>>>

安装solc编译器，请参见**https://solidity-cn.readthedocs.io/zh/latest/installing-solidity.html**。

    .. code-block:: bash

        solc --version
        // solc, the solidity compiler commandline interface
        // Version: 0.5.9+commit.c68bc34e.Darwin.appleclang
        // 以上打印说明编译器安装成功

2.合约样例
>>>>>>>>>>>

.. code-block:: c++
    :linenos:
	
    pragma solidity >=0.0.0;

    contract Counter {
        address owner;
        mapping (string => uint256) values;

        constructor() public{
            owner = msg.sender;
        }

        function increase(string memory key) public payable{
            values[key] = values[key] + 1;
        }

        function get(string memory key) view public returns (uint) {
            return values[key];
        }

        function getOwner() view public returns (address) {
            return owner;
        }

    }

.. note::

    - solidity合约相关文档请参见 **https://github.com/ethereum/solidity** 。

    - 更多的Solidity语言合约例子在超级链项目的 **core/contractsdk/evm/example** 以及 **https://github.com/OpenZeppelin/openzeppelin-contracts** 里面寻找。

3.合约编译
>>>>>>>>>>>

Solidity合约使用如下命令来编译合约

.. code-block:: go
    :linenos:
	
    // 通过solc编译合约源码
    solc --bin --abi Counter.sol -o .
    // 合约二进制文件和abi文件分别存放在当前目录下，Counter.bin和Counter.abi

- ``--bin`` ：表示需要生成合约二进制文件
- ``--abi`` ：表示需要生成合约abi文件，用于合约方法以及参数编解码
- ``-o``：表示编译结果输出路径

合约部署
>>>>>>>>>>>>>
Solidity合约部署完整命令如下

.. code-block:: bash
	
    $ xchain-cli evm deploy --account XC1111111111111111@xuper --cname counterevm  --fee 5200000 Counter.bin --abi Counter.abi


4. 合约调用

    调用solidity合约。通过合约名直接发起合约调用和查询。

    .. code-block:: bash

        # 调用solidity合约，increase方法，counterevm为合约名
        xchain-cli evm invoke --method increase -a '{"key":"test"}' counterevm --fee 22787517 --abi Counter.abi
        # 调用结果
        # contract response:
        # The gas you cousume is: 65
        # The fee you pay is: 22787517
        # Tx id: 94655ab00188de70c3ef2f91b9db0d156142ce92f91a5da20f0f1fc7830fb700

        # 调用solidity合约，get方法，counterevm为合约名
        xchain-cli native query --method Get -a '{"key":"test"}' counterevm --abi Counter.abi
        # 调用结果，其中0表示返回值的次序，1为返回值
        # key,value: 0 1

5. 超级链账户与EVM账户地址转换

    超级链有普通地址、合约账户以及合约名，这三类账户在EVM运行时需要转换为以太坊的地址类型（16进制编码字符串，形如0x1234567890abcdef1234567890abcdef12345678格式）。超级链提供了上述三个地址与EVM地址类型转换工具。

    .. code-block:: bash

        # xchain合约账户地址转evm地址，contract-account表示超级链合约账户
        xchain-cli evm addr-trans -t x2e -f XC1111111111111113@xuper
        result, 3131313231313131313131313131313131313133    contract-account
        
        # evm地址转xchain合约账户，contract-account表示超级链合约账户
        xchain-cli evm addr-trans -t e2x -f 3131313231313131313131313131313131313133
        result, XC1111111111111113@xuper     contract-account        
        
        # xchain普通账户地址转evm地址，xchain表示超级链普通账户
        xchain-cli evm addr-trans -t e2x -f 93F86A462A3174C7AD1281BCF400A9F18D244E06
        result, dpzuVdosQrF2kmzumhVeFQZa1aYcdgFpN   xchain        
        
        # xchain普通账户地址转evm地址，xchain表示超级链普通账户
        xchain-cli evm addr-trans -t x2e -f dpzuVdosQrF2kmzumhVeFQZa1aYcdgFpN
        result, 93F86A462A3174C7AD1281BCF400A9F18D244E06   xchain      
        
        # xchain合约名地址转evm地址，contract-name表示超级链合约名
        xchain-cli evm addr-trans -t x2e -f storagedata11
        result, 313131312D2D2D73746F72616765646174613131   contract-name    
        
        # evm地址转xchain合约名，contract-name表示超级链合约名
        xchain-cli evm addr-trans -t e2x -f 313131312D2D2D73746F72616765646174613131
        result, storagedata11   contract-name

    - ``x2e`` ：表示超级链地址转换为EVM地址
    - ``e2x`` ：表示EVM地址转换为超级链地址。



    超级链有普通地址、合约账户以及合约名，这三类账户在EVM运行时需要转换为以太坊的地址类型（16进制编码字符串，形如0x1234567890abcdef1234567890abcdef12345678格式）。超级链提供了上述三个地址与EVM地址类型转换工具。

    .. code-block:: bash

        # xchain合约账户地址转evm地址，contract-account表示超级链合约账户
        xchain-cli evm addr-trans -t x2e -f XC1111111111111113@xuper
        result, 3131313231313131313131313131313131313133    contract-account
        
        # evm地址转xchain合约账户，contract-account表示超级链合约账户
        xchain-cli evm addr-trans -t e2x -f 3131313231313131313131313131313131313133
        result, XC1111111111111113@xuper     contract-account        
        
        # xchain普通账户地址转evm地址，xchain表示超级链普通账户
        xchain-cli evm addr-trans -t e2x -f 93F86A462A3174C7AD1281BCF400A9F18D244E06
        result, dpzuVdosQrF2kmzumhVeFQZa1aYcdgFpN   xchain        
        
        # xchain普通账户地址转evm地址，xchain表示超级链普通账户
        xchain-cli evm addr-trans -t x2e -f dpzuVdosQrF2kmzumhVeFQZa1aYcdgFpN
        result, 93F86A462A3174C7AD1281BCF400A9F18D244E06   xchain      
        
        # xchain合约名地址转evm地址，contract-name表示超级链合约名
        xchain-cli evm addr-trans -t x2e -f storagedata11
        result, 313131312D2D2D73746F72616765646174613131   contract-name    
        
        # evm地址转xchain合约名，contract-name表示超级链合约名
        xchain-cli evm addr-trans -t e2x -f 313131312D2D2D73746F72616765646174613131
        result, storagedata11   contract-name

    - ``x2e`` ：表示超级链地址转换为EVM地址
    - ``e2x`` ：表示EVM地址转换为超级链地址。


