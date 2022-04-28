# dtguai-encrypt-spring-boot-starter

[![AUR](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg)](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/blob/master/LICENSE)  [![](https://img.shields.io/badge/version-1.1.3-brightgreen.svg)](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter)  [![](https://img.shields.io/badge/spring--boot-2.6.7-green.svg)](http://spring.io/projects/spring-boot)  [![](https://img.shields.io/badge/JDK-8+-green.svg)](https://www.oracle.com/technetwork/java/javase/downloads/index.html)

## 开源不易，如果对您有帮助，请点右上角 “Star” 支持一下 谢谢！

## 介绍

数据传输加密/解密及数字证书(数据防改)的组件  
SpringBoot 通过注解实现数据加密与解密,  
支持多种加密方式(MD5/SHA/AES/DES/RSA)及国密算法(SM2/SM3/SM4),  
加密解密,注解,springboot,java,加密,解密,数字证书,国密,加签,验签,等全套服务

##  示例

- [传送门](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/tree/master/dtguai-encrypt-example)


## 加密/解密支持

- 可进行加密的方式有：

    -
        - [x] [MD5](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E5%8A%A0%E5%AF%86/MD5)
    -
        - [x] [SHA-224 / 256 / 384 / 512](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E5%8A%A0%E5%AF%86/SHA-224,256,384,512)
    -
        - [x] [AES](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E5%8A%A0%E5%AF%86/AES)
    -
        - [x] [DES](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E5%8A%A0%E5%AF%86/DES)
    -
        - [x] [RSA](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E5%8A%A0%E5%AF%86/RSA)
    -
        - [x] [SM2](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E5%8A%A0%E5%AF%86/%E5%9B%BD%E5%AF%86/SM2)
    -
        - [x] [SM3](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E5%8A%A0%E5%AF%86/%E5%9B%BD%E5%AF%86/SM3)
    -
        - [x] [SM4](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E5%8A%A0%E5%AF%86/%E5%9B%BD%E5%AF%86/SM4)

- 可进行解密的方式有：

    -
        - [x] [AES](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E8%A7%A3%E5%AF%86/AES)
    -
        - [x] [DES](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E8%A7%A3%E5%AF%86/DES)
    -
        - [x] [RSA](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E8%A7%A3%E5%AF%86/RSA)
    -
        - [x] [SM2](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E8%A7%A3%E5%AF%86/%E5%9B%BD%E5%AF%86/SM2)
    -
        - [x] [SM4](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E8%A7%A3%E5%AF%86/%E5%9B%BD%E5%AF%86/SM4)

## 数字证书

### 验签/加签

-
    - [x] [验签](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E6%95%B0%E5%AD%97%E7%AD%BE%E5%90%8D/%E9%AA%8C%E7%AD%BE/sign)


-
    - [x] [加签](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E6%95%B0%E5%AD%97%E7%AD%BE%E5%90%8D/%E5%8A%A0%E7%AD%BE/SignOut)

## 快速开始

### 环境说明

1.配置环境jdk1.8及以上

### 使用方式

#### 1.Maven依赖

```xml
<dependency>
    <groupId>com.dtguai</groupId>
    <artifactId>dtguai-encrypt-spring-boot-starter</artifactId>
    <version>1.1.3</version>
</dependency>
```

#### 2. 添加配置文件

- [说明](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E5%8F%82%E6%95%B0%E8%AF%B4%E6%98%8E/%E9%85%8D%E7%BD%AE/%E9%85%8D%E7%BD%AE%E8%AF%B4%E6%98%8E)

## 注解参数说明

- [加密](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E5%8F%82%E6%95%B0%E8%AF%B4%E6%98%8E/%E6%B3%A8%E8%A7%A3/%E5%8A%A0%E5%AF%86)

- [解密](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E5%8F%82%E6%95%B0%E8%AF%B4%E6%98%8E/%E6%B3%A8%E8%A7%A3/%E8%A7%A3%E5%AF%86)

- [签名(sign)](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E5%8F%82%E6%95%B0%E8%AF%B4%E6%98%8E/%E6%B3%A8%E8%A7%A3/%E7%AD%BE%E5%90%8D)

##  更新日志

- [日志](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E6%9B%B4%E6%96%B0%E8%AE%B0%E5%BD%95?sort_id=4355068)

## 算法说明

- [算法分类](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E7%AE%97%E6%B3%95%E5%88%86%E7%B1%BB%20?sort_id=4355085)

- [国密算法](https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter/wikis/%E5%9B%BD%E5%AF%86%E7%AE%97%E6%B3%95?sort_id=4355178)

