# dtguai-app-api


<a href="http://spring.io/projects/spring-boot">
            <img src="https://img.shields.io/badge/spring--boot-2.1.3-green.svg" alt="spring-boot">
</a>     
 
# 介绍
代码生成器源码: https://gitee.com/gouliang/dtguai-code_generator  

## 最新更新2019年6月26日15:46:57
1.增加了RSA解密(加密数据最大长度 根据钥匙而定默认长度(2048/8)-11)   
2.增加了RSA加密   
3.升级了druid 连接池到17 并且修改了配置文件   
4.修改了解密规则 安全性更高   
5.提高了 数字签名的性能   
6.删除了timeout标签 集成在数据校验   
7.增加 数据校验过期时间 使得超过指定时间无法通过校验 提高安全性   

```java
@RestController
@RequestMapping("/test")
public class TestController {
    
    //加密并且设置超时时间
    @RSADecryptBody(timeOut = 60 * 1000)
    public String api(){
        return "小邱爱小付";
    }

}
```


## 加入新标签    
@sign        数字签名验证  支持body 和 Param  
@login       需要登录验证  
@loginUser   获取用户信息  

### @sign使用介绍:
test.java.com.dtguai.app.sign.SignTest.java  
测试类  里面有详细规则 每一步 都打印了日志方便观察

-----------------------------------------------------------   

### 加密解密 标签  
#### 解密
##### @AESDecryptBody   
对含有@RequestBody注解的控制器的请求数据采用AES方式进行解密  
##### @DESDecryptBody    
对含有@RequestBody注解的控制器的请求数据采用DES方式进行解密  
##### @RSADecryptBody  
对含有@RequestBody注解的控制器的请求数据采用DES方式进行解密    
#### 加密
##### @AESEncryptBody   
对含有@ResponseBody注解的控制器的响应值采用AES方式进行加密  
##### @DESEncryptBody   
对含有@ResponseBody注解的控制器的响应值采用DES方式进行加密  
##### @RSAEncryptBody   
对含有@ResponseBody注解的控制器的响应值采用RSA方式进行加密  
##### @MD5EncryptBody   
对含有@ResponseBody注解的控制器的响应值采用MD5方式进行加密  
##### @SHAEncryptBody   
含有@ResponseBody注解的控制器的响应值采用SHA方式进行加密  

## 数字签名校验
- 参数配置
在项目的`application.yml`或`application.properties`文件中进行参数配置，例如：
```yaml
sign:
  key: qyxVsFzp
```
- 对控制器响应体进行数字签名验证
```java
@RestController
@RequestMapping("/test")
public class TestController {

    @Sign
    public String api(){
        return "小邱爱小付";
    }

}
```

## 加密解密支持
- 可进行加密的方式有：
    - - [x] MD5
    - - [x] SHA-224 / 256 / 384 / 512
    - - [x] AES
    - - [x] DES  
    - - [x] RSA  
- 可进行解密的方式有：
    - - [x] AES
    - - [x] DES  
    - - [x] RSA  

- 参数配置
在项目的`application.yml`或`application.properties`文件中进行参数配置，例如：
```yaml
encrypt:
  body:
    aes-key: xiaoFuLoveXiaoQiu #AES加密秘钥
    des-key: xiaoFuLoveXiaoQiu #DES加密秘钥
    #RSA 生成公钥/私钥
    rsa-key: xiaoFuLoveXiaoQiu
    #RSA私钥
    rsa-pir-key: xx
    #RSA公钥
    rsa-pub-key: xx
```

- 对控制器响应体进行解密
```java
@Controller
@RequestMapping("/test")
public class TestController {

    @PostMapping
    @ResponseBody
    @AESDecryptBody
    public String api(@RequestBody UserForm user){
        return "小邱爱小付";
    }

}
```
或者使用`@RestController`对整个控制器的方法响应体都进行解密：
```java
@RestController
@AESDecryptBody
@RequestMapping("/test")
public class TestController {

    @PostMapping
    public String api(@RequestBody UserForm user){
        return "小邱爱小付";
    }

}
```

- 对控制器响应体进行加密
```java
@Controller
@RequestMapping("/test")
public class TestController {

    @PostMapping
    @ResponseBody
    @AESEncryptBody
    public String api(){
        return "小邱爱小付";
    }

}
```
或者使用`@RestController`对整个控制器的方法响应体都进行加密：
```java
@RestController
@AESEncryptBody
@RequestMapping("/test")
public class TestController {

    @PostMapping
    public String api(){
        return "小邱爱小付";
    }

}
```


---------------------------------------------------------------   
算法分类  

根据加密结果是否可以被解密，算法可以分为可逆加密和不可逆加密（单向加密），从这个意义上来说，单向加密只能称之为加密算法而不是加解密算法。对于可逆加密，又可以根据密钥的的对称性分为对称加密和非对称加密。具体的分类结构如下：

◦可逆加密◦对称加密：DES，3DES，AES，PBE   
◦非对称加密：RSA，DSA，ECC（椭圆曲线加密算法）  

◦不可逆加密（单向加密）：MD5，SHA，HMAC   

很显然，如果是签名，我们可以用不可逆加密，如果是body体的话，我们需要用可逆算法，去解出相应数据才能进行业务处理。

RSA介绍
不管明文长度是多少，RSA 生成的密文长度总是固定的。  
但是明文长度不能超过密钥长度。    
比如 Java 默认的 RSA 加密实现不允许明文长度超过密钥长度减去 11(单位是字节，也就是 byte)。     
也就是说，如果我们定义的密钥(我们可以通过 java.security.KeyPairGenerator.initialize(int keysize)     
 来定义密钥长度)长度为 1024(单位是位，也就是 bit)，生成的密钥长度就是 1024位 / 8位/字节 = 128字节，      
 那么我们需要加密的明文长度不能超过 128字节 -11 字节 = 117字节。      
也就是说，我们最大能将 117 字节长度的明文进行加密，     
否则会出问题     
(抛诸如 javax.crypto.IllegalBlockSizeException: Data must not be longer than 53 bytes 的异常)。   
而 BC 提供的加密算法能够支持到的 RSA 明文长度最长为密钥长度。    

-----------------------------------------------------------------   
遇到的几个问题记录一下   
1.过滤器 拿不到 controller 的注解  
2.拦截器 无法改变 request 的值 不管是 body 还是 ParameterMap   
3.自定义注解 无法动态 set反射类参数 所以自能用 @requestbody   
4.request getInputStream 因为是流 只能读取一次 如果想多次必须在过滤器搞 拦截器不成因为无法set值  
5.多次加密 需要多次获取信息   
6.需要更灵活的标签   
7.尽量减少解密次数提高性能  
8.aop自定义注解解密不能搞对象类型 因为无法 预支对象类型和 set值   
