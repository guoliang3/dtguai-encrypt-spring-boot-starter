# dtguai-encrypt-spring-boot-starter

[![AUR](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg)](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/blob/master/LICENSE)  [![](https://img.shields.io/badge/version-1.1.1-brightgreen.svg)](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter)  [![](https://img.shields.io/badge/spring--boot-2.6.6-green.svg)](http://spring.io/projects/spring-boot)

# 介绍

数据传输加密/解密及数字证书(数据防改)的组件  
SpringBoot 通过注解实现数据加密与解密,  
支持多种加密方式(MD5/SHA/AES/DES/RSA)及国密算法(SM2/SM3/SM4),  
加密解密,注解,springboot,java,加密,解密,数字证书,国密,加签,验签,等全套服务

##  [dtguai-encrypt-spring-boot-starter示例](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/tree/master/dtguai-encrypt-example)



## 加密/解密支持

- 可进行加密的方式有：

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
        - [x] AES
    -
        - [x] DES
    -
        - [x] RSA
    -
        - [x] SM2
    -
        - [x] SM4



## 快速开始

### 使用方式

#### 注: jdk8+

```xml
    <properties>
    	<java.version>8</java.version>
    </properties>
```

#### Maven依赖

```xml
    <dependency>
        <groupId>com.dtguai</groupId>
        <artifactId>dtguai-encrypt-spring-boot-starter</artifactId>
        <version>1.1.1</version>
    </dependency>
```


### 加密(RestController将指定输出数据加密)

#### MD5

- [@EncryptBody(value = EncryptBodyMethod.MD5)](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E5%8A%A0%E5%AF%86/MD5)

#### SHA-224 / 256 / 384 / 512
- [@EncryptBody(value = EncryptBodyMethod.SHA)](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E5%8A%A0%E5%AF%86/SHA-224,256,384,512)

#### AES
- [@EncryptBody(value = EncryptBodyMethod.AES)](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E5%8A%A0%E5%AF%86/AES)

#### DES
- [@EncryptBody(value = EncryptBodyMethod.DES)](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E5%8A%A0%E5%AF%86/DES)

#### RSA
- [@EncryptBody(value = EncryptBodyMethod.RSA)](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E5%8A%A0%E5%AF%86/RSA)

#### 国密

##### SM2
- [@EncryptBody(value = EncryptBodyMethod.SM2)](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E5%8A%A0%E5%AF%86/%E5%9B%BD%E5%AF%86/SM2)

##### SM3
- [@EncryptBody(value = EncryptBodyMethod.SM3)](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E5%8A%A0%E5%AF%86/%E5%9B%BD%E5%AF%86/SM3)

##### SM4
- [@EncryptBody(value = EncryptBodyMethod.SM4)](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E5%8A%A0%E5%AF%86/%E5%9B%BD%E5%AF%86/SM4)

### 解密

#### AES
- [@DecryptBody(value = DecryptBodyMethod.AES)](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E8%A7%A3%E5%AF%86/AES)

#### DES
- [@DecryptBody(value = DecryptBodyMethod.DES)](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E8%A7%A3%E5%AF%86/DES)

#### RSA
- [@DecryptBody(value = DecryptBodyMethod.RSA)](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E8%A7%A3%E5%AF%86/RSA)

#### 国密

##### SM2
- [@DecryptBody(value = DecryptBodyMethod.SM2)](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E8%A7%A3%E5%AF%86/%E5%9B%BD%E5%AF%86/SM2)

##### SM4
- [@DecryptBody(value = DecryptBodyMethod.SM4)](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E8%A7%A3%E5%AF%86/%E5%9B%BD%E5%AF%86/SM4)



### 数字证书

#### 验签
- [@Sign](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E6%95%B0%E5%AD%97%E7%AD%BE%E5%90%8D/%E9%AA%8C%E7%AD%BE/sign)

#### 加签
- [@SignOut](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E6%95%B0%E5%AD%97%E7%AD%BE%E5%90%8D/%E5%8A%A0%E7%AD%BE/SignOut)

### 标签主要参数介绍

value 默认为 DecryptBodyMethod.AES;

long 默认为0不限制超时时间

## 配置文件

- [说明](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E9%85%8D%E7%BD%AE%E8%AF%B4%E6%98%8E)


##  更新日志

- [日志](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E6%9B%B4%E6%96%B0%E8%AE%B0%E5%BD%95?sort_id=4355068)

## 算法说明

- [算法分类](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E7%AE%97%E6%B3%95%E5%88%86%E7%B1%BB%20?sort_id=4355085)

- [国密算法](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E5%9B%BD%E5%AF%86%E7%AE%97%E6%B3%95?sort_id=4355178)

