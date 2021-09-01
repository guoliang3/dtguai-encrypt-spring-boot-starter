package com.dtguai.test.singleton;

import com.dtguai.encrypt.util.ISecurity;
import com.dtguai.encrypt.util.security.Md5Util;

/**
 * 描述
 *
 * @author guo
 * @date 2021/9/1 15:06
 */
public enum EnumSingleton {

    MD5,

    ;

    EnumSingleton() {
        System.out.println("init");
    }

    public ISecurity getInstance() {
        return new Md5Util();
    }

    public static void main(String[] args) {


        ISecurity instance = EnumSingleton.MD5.getInstance();

        ISecurity instance2 = EnumSingleton.MD5.getInstance();

        System.out.println(instance);
        System.out.println(instance2);
        System.out.println(instance == instance2);
    }

}

