package com.dtguai.test.singleton;

import com.dtguai.encrypt.util.ISecurity;
import com.dtguai.encrypt.util.security.Md5Util;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述
 *
 * @author guo
 * @date 2022年8月23日17:19:26
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum EnumSingleton2 {

    /**
     * md5
     */
    MD5(new Md5Util()),

    ;

    /**
     * 接口
     */
    ISecurity iSecurity;


    public static void main(String[] args) {

        ISecurity instance = EnumSingleton2.MD5.getISecurity();

        ISecurity instance2 = EnumSingleton2.MD5.getISecurity();

        System.out.println(instance);
        System.out.println(instance2);
        System.out.println(instance == instance2);
    }

}

