# dtguai-encrypt-spring-boot-starter

<a href="http://spring.io/projects/spring-boot">
            <img src="https://img.shields.io/badge/spring--boot-2.5.3-green.svg" alt="spring-boot">
</a>     

# 介绍

数据传输加密/解密及数字证书(数据防改)的组件

## springboot 使用demo

<a href="https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/tree/master/dtguai-encrypt-example">
  demo 示例
 dtguai-encrypt-example
</a> 

## 加密/解密支持

- 可进行加密的方式有：
  -
    -
        - [x] MD5
    -
        - [x] SHA-224 / 256 / 384 / 512
    -
        - [x] AES
    -
        - [x] DES
    -
        - [x] RSA
    -
        - [x] SM2
    -
        - [x] SM3
    -
        - [x] SM4
      
- 可进行解密的方式有：
  -
    -
      - [x] AES
    -
        - [x] DES
    -
        - [x] RSA
    -
        - [x] SM2
    -
        - [x] SM4

## 开放标签

##### @DecryptBody(value = DecryptBodyMethod.DES)

##### @EncryptBody(value = EncryptBodyMethod.DES)

##### @DecryptBody(value = DecryptBodyMethod.AES)

##### @EncryptBody(value = EncryptBodyMethod.AES)

##### @DecryptBody(value = DecryptBodyMethod.RSA)

##### @EncryptBody(value = EncryptBodyMethod.RSA)

##### @DecryptBody(value = DecryptBodyMethod.MS2)

##### @EncryptBody(value = EncryptBodyMethod.SM2)

##### @EncryptBody(value = EncryptBodyMethod.SM3)

##### @DecryptBody(value = DecryptBodyMethod.SM4)

##### @EncryptBody(value = EncryptBodyMethod.SM4)

##### @EncryptBody(value = EncryptBodyMethod.MD5)

默认为AES

### 标签主要参数介绍

value 默认为 DecryptBodyMethod.AES; long 默认为0不限制超时时间

## 使用方式

## 注:开发使用的版本为 jdk8+

```yaml
    <properties>
    <java.version>8</java.version>
    </properties>
```

## Maven依赖

```
    <dependency>
        <groupId>com.dtguai</groupId>
        <artifactId>dtguai-encrypt-spring-boot-starter</artifactId>
        <version>1.1.0</version>
    </dependency>
```

```java

@RestController
@RequestMapping("/test")
public class TestController {

    //需要数字证书
    @Sign
    //des解密
    @DecryptBody(value = DecryptBodyMethod.DES)
    //des输出加密
    @EncryptBody(value = EncryptBodyMethod.DES)
    public String api() {
        return "小邱爱小付";
    }

}
```

## 入参对象使用

```java
    @Sign
@DecryptBody(value = DecryptBodyMethod.RSA)
@EncryptBody(value = EncryptBodyMethod.RSA)
public ApiResponse<?> rsaSign(@RequestBody TestRsaDataSecretForm user){

        }

        需要在原有的入参form添加 sign 和 dataSecret 字段
        揭秘原理:接到解密数据和sign数据会解密完放到对应对象字段

public class TestRsaDataSecretForm {

    @ApiModelProperty(hidden = true)
    private String mobile;

    @ApiModelProperty(hidden = true)
    private String password;

    @ApiModelProperty(hidden = true)
    private String timestamp;

    @ApiModelProperty(value = "数字签名", example = "")
    private String sign;

    @NotBlank(message = "加密数据不能为空", groups = {LoginGroup.class})
    private String dataSecret;

}
```

## 加密注意事项

```java
public class ApiResponse<T> {
    //返回对象中 需要有result 字段 加密是根据此字段加密
    @JsonInclude(Include.NON_NULL)
    private T result;
```

如果名字不是result 请使用自定义 @EncryptBody(encryptMsgName=xxx)

## 配置文件

- 参数配置 在项目的`application.yml`或`application.properties`文件中进行参数配置，例如：

```yaml
dtguai:
  encrypt:
    body:
      #AES加密秘钥
      aes-key: xiaoFuLoveXiaoQi
      #DES加密秘钥
      des-key: xiaoFuLoveXiaoQiu
      #RSA 生成公钥/私钥
      rsa-key: xiaoFuLoveXiaoQiu
      #RSA私钥
      rsa-pir-key: MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCi7oI1cAnv5YPHPbu2NkDqgGlEWNVM6na6pL0xZoS1Uv0KmWTHNW5gaFmnOxiqjU9wxrwCSPMCpGDpUz/pDY/46mRUO+acdf29F0ERZIdLAm+H8Jk6Z+3QwB9YLm/YQm56RSqJL/Vw4ejVzx2OHh6TJmCeAl2gnKQ5UFgynBJN88RImtodAt2DTrFWCZ7WYcIjzAemYTNcoZQliM7Jt4CSh5acUer1opG2m3nVRaE4GEO6ZScyHndzjLwLKSbLrMvCXSEbpqH9NLGdY6wL7gSVWU6fhUYSZQS2CjDMXOMQvy+NQZ4qEcGfLJ6NjmPPfvO3Vf8aTi+0Dndm1vB2m0hLAgMBAAECggEBAJx4ZT+ge0qRpf4/wPd6HtmVvIJQtXeKa79O2gqJI8VnD2+QZIITRA7956t8S3HvB22MzZ18JTRSXGd29ZeA2NT5dKSYah20Cpv/qxNi8bMNgcKRQvYePPsOpotu/SY4lhSCPBlHn3Rq38oFf6KuNjqmzn6wUu4dwHDyQeFIlSOZ/0MdcIzHfkroogVFuo9G7jTXb/HJA84wWC/EG41GqIE0Zu/8u+gkDQsNggt0rU3ntPinudwp9sySmBY/7oK1+IpucM9nA9j7N5MmyXK3lLaWSGD4cGS5m4gy+cUnPVPNFMAhMcAzVFWv9mGTdsOzeGheCoLy9D3+IwgNUC68gmECgYEA3YjJWAWVzI/kspjGKBzT0L1f3t8nju5H14d+xvAGyN/FkD7+NEzkSE0lNCh4gK3YbCQeYKItC9T2VgPWIfkksXbKjKKZ8gMvMM3hCf1y7KUcghXwGNNRcLO+DpzduUQ2g122mPLsvjsOpRX1ByasBD7wQ3rf3XKY+XrXJJ0BY3kCgYEAvEe15j2QDOoMAfxNdlzf5Z8d34JwnsfNYdzpz4zzvAjnXSe6FawabU6jgaJUH8oMSYIIpYjf3HUu0CNTKYRHezz9nXiFgNFwmRmkmjEqDAMIjVkN3rsaP3A7ST24gT7xVcua3BRIITv2KVwYZYWZN5yvjeqXoD3zg9XSYsUntOMCgYBzwvSjgFeky1RQVzDE6TtuCmc8iROcxrrXzz/aKLcC5JvSIninT77CYT2docBGTZGYAM+240fytNf8XojYu94GtfJlxtn28t8H+60qkTwqmKTq/Re3gUU/RU19SU87bn+l6aRvDBHV3fprHawqjnS4y0K1oFG24Bk71Irz4O9G0QKBgCA+x9BeysTrcJMGet3CmjXhQmR9GnyXC32vL/vzz+psO/OgUiZUC1KdHTOecXngSKpuMrzm7C/9gb7zzdJWbUeV8nl9op+lPvt5gM9HjEPyYQyUK+Pxd1VWC1FH2MmJ1hsze5olfxTyB/6dXzBD6TG1C/vqKX2wHnx9qkJwMZSxAoGBAIo9aDm/Y1+I3wjm+rM2Tk43a56y1UdE6EAJ0fFv8cknxPOPnDvUtNFqDEzIbv3QLeGZkZyfgVGHnc4f2PZ//k+3okmxRF08uVAQIX8V5yrvbA2u0DkTW05gfajAoYjHa8PRL1UeSt9YRVC6h3jT3Kc/GMjoSXLMTmOwAFUDM4ep
      #RSA公钥
      rsa-pub-key: MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAou6CNXAJ7+WDxz27tjZA6oBpRFjVTOp2uqS9MWaEtVL9CplkxzVuYGhZpzsYqo1PcMa8AkjzAqRg6VM/6Q2P+OpkVDvmnHX9vRdBEWSHSwJvh/CZOmft0MAfWC5v2EJuekUqiS/1cOHo1c8djh4ekyZgngJdoJykOVBYMpwSTfPESJraHQLdg06xVgme1mHCI8wHpmEzXKGUJYjOybeAkoeWnFHq9aKRtpt51UWhOBhDumUnMh53c4y8Cykmy6zLwl0hG6ah/TSxnWOsC+4ElVlOn4VGEmUEtgowzFzjEL8vjUGeKhHBnyyejY5jz37zt1X/Gk4vtA53ZtbwdptISwIDAQAB
      #SM2私钥
      sm2-pir-key: MIGTAgEAMBMGByqGSM49AgEGCCqBHM9VAYItBHkwdwIBAQQgEs/mFVKRAQjqpAFFOuZGT9or50YMe/ipaiDFAathMTegCgYIKoEcz1UBgi2hRANCAATjybx66wZBVHyMGyZkysqwR7F+wOEzRwi8Mu3Phv6XVLV3qyhtgDa44ujNsHhGbCP4fxdiA7NS2AfeJn6RTVND
      #SM2对方公钥
      sm2-pub-key: MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAE48m8eusGQVR8jBsmZMrKsEexfsDhM0cIvDLtz4b+l1S1d6sobYA2uOLozbB4Rmwj+H8XYgOzUtgH3iZ+kU1TQw==
      #SM4加密秘钥
      sm4-key: xiaoFuLoveXiaoQi
    #自定义返回类中需要加密的字段名称 默认为result
    #result-name: cxbn
  sign:
    key: qyxVsFzp
    #自定义数字证书 过滤值 默认为"token","sign","dataSecret"
#   ignore:
#     - token
#     - sign
#     - dataSecret
#     - name
#     - klr
```

- 只需要对控制器响应体进行数字签名验证

```java

@RestController
@RequestMapping("/test")
public class TestController {

    @Sign
    public String api() {
        return "小邱爱小付";
    }

}
```

- 配置文件可配置参数

```java
private String aesKey;

private String desKey;

private String rsaKey;

private String encoding="UTF-8";

private String rsaPirKey;

private String rsaPubKey;

private String sm2PirKey;

private String sm2PubKey;

private String sm4Key;

/**
 * Aes密码算法及填充方式
 */
private String aesCipherAlgorithm="AES/GCM/NoPadding";

/**
 * Aes密码算法及填充方式
 */
private String desCipherAlgorithm="DES/ECB/PKCS5Padding";
```

- 更新日志
  <a href="https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E6%9B%B4%E6%96%B0%E8%AE%B0%E5%BD%95?sort_id=4355068">
  更新日志
  </a>

---------------------------------------------------------------   

- 算法分类
  <a href="https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E7%AE%97%E6%B3%95%E5%88%86%E7%B1%BB%20?sort_id=4355085">
  算法分类
  </a>

-------------------------------------------------------------------------------------------------------------------

- 国密算法
  <a href="https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E5%9B%BD%E5%AF%86%E7%AE%97%E6%B3%95?sort_id=4355178">
  国密算法
  </a>

