package org.beykery.contract.deployer;

import org.beykery.contract.deployer.contract.TestToken_sol_TestToken;
import org.beykery.eth.WillWallet;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;

import java.math.BigInteger;

/**
 * check
 */
public class Main
{
  /**
   * 检查所有账户的erc20持有情况
   *
   * @param args
   */
  public static final void main(String... args) throws Exception
  {
//    String privateKey = args[0];//这里填入你的eth私钥
//    String priceString = args[1];//这里填入你的价格（单位gwei），可以去 https://ethgasstation.info 查找一个SafeLow建议值

    String privateKey = "0xxxxxxxxxxxxxxx";//这里填入你的eth私钥
    String priceString = "4";//这里填入你的价格（单位gwei），可以去 https://ethgasstation.info 查找一个SafeLow建议值


    WillWallet wallet = new WillWallet(privateKey);
    String url = "https://mainnet.infura.io/iaosV7jPAld2pmo3he27";
    Web3j web3j = Web3j.build(new HttpService(url));
    Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().sendAsync().get();
    String clientVersion = web3ClientVersion.getWeb3ClientVersion();
    System.out.println(clientVersion);
    EthGetBalance bl = web3j.ethGetBalance(wallet.getAddress(), DefaultBlockParameterName.LATEST).send();
    System.out.println("账上eth余额 : " + bl.getBalance());

    BigInteger gwei = new BigInteger("1000000000");
    BigInteger price = gwei.multiply(new BigInteger(priceString));
    BigInteger limit = new BigInteger("4800000");
    RemoteCall<TestToken_sol_TestToken> call = TestToken_sol_TestToken.deploy(web3j, wallet.getCredentials(), price, limit);//TestToken_sol_TestToken可以改为你自己的合约
    TestToken_sol_TestToken tt = call.send();
    System.out.println("部署合约 " + tt.getContractAddress() + " 成功 .");

  }

  /**
   * 部署合约
   *
   * @param c           合约类
   * @param privateKey  私钥
   * @param priceString 价格（gwei）
   */
  public static void deploy(Class<? extends Contract> c, String privateKey, String priceString) throws Exception
  {
    WillWallet wallet = new WillWallet(privateKey);
    String url = "https://mainnet.infura.io/iaosV7jPAld2pmo3he27";
    Web3j web3j = Web3j.build(new HttpService(url));
    Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().sendAsync().get();
    String clientVersion = web3ClientVersion.getWeb3ClientVersion();
    System.out.println(clientVersion);
    EthGetBalance bl = web3j.ethGetBalance(wallet.getAddress(), DefaultBlockParameterName.LATEST).send();
    System.out.println("账上eth余额 : " + bl.getBalance());

    BigInteger gwei = new BigInteger("1000000000");
    BigInteger price = gwei.multiply(new BigInteger(priceString));
    BigInteger limit = new BigInteger("4800000");
    RemoteCall<TestToken_sol_TestToken> call = TestToken_sol_TestToken.deploy(web3j, wallet.getCredentials(), price, limit);
    TestToken_sol_TestToken tt = call.send();
    System.out.println("部署合约 " + tt.getContractAddress() + " 成功 .");
  }

}
