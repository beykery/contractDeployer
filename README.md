# contractDeployer  

以太坊智能合约的部署

##工具准备  

依次执行下列安装  

`npm install -g solc `  
`npm install -g solc-cli`  
`brew tap web3j/web3j`  
`brew install web3j`  

##编写合约  

请参考根目录下的TestToken.sol  
这是一个符合erc20规范的代币实现  
如果你想发自己的币，那么可以修改下文件名  
比如：XXXToken.sol，然后打开此文件，修改  
下最后一个类TestToken为XXXToken，  
string constant public name 的值改为XXXToken  
string constant public symbol 的值改为XXX  
即可，如果你想修改发行量和精度，则修改string constant public symbol  
和uint8 constant public decimals 的值  

##编译合约  

在根目录执行 ：  
`solcjs XXXToken.sol --bin --abi --optimize -o ./contracts/`  
`web3j solidity generate ./contracts/XXXToken_sol_XXXToken.bin ./contracts/XXXToken_sol_XXXToken.abi -o ./src/main/java -p org.beykery.contract.deployer.contract`  

##部署合约  

如果以上执行无误，则会发现在./src/main/java/org/beykery/contract/deployer/contract  
下会生成一个XXXToken_sol_XXXToken.java，这个就是合约的代理类，下面我们通过执行它的deploy  
方法即可部署合约  

打开org.beykery.contract.deployer.Main类，main方法下修改变量privateKey值为你的私钥  
修改变量priceString的值为价格（单位为gwei），价格可以查询汽油站：https://ethgasstation.info  
获取一个合适的值，一般使用不低于SafeLow建议值即可；另外main方法里面的TestToken_sol_TestToken  
需要换成你的XXXToken_sol_XXXToken类名  

以上修改完毕，在运行main方法之前，需要保证你私钥对应的地址上有足够多的ether来付矿工费  
比如你填写的priceString值为4，则计算矿工费： 4 * 1000000000 * 500000 wei 也就是  
2000000000000000 wei ，等于0.002 ether， 不会超过这个值，比这个值小一点。  

保证你账户余额大于矿工费，那么就可以执行合约的部署了，也就是运行main函数。  

执行完，如果成功则返回合约的地址，如果抛出异常说矿工费不足，则再买点ether并提币到你的私钥对应的  
地址上吧，之后再次尝试运行main函数  

Donation:  `0x8aCc161acB2626505755bBF36184841B8c099806`  


good luck !  

enjoy it . 




