package com.dtguai;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 描述
 *
 * @author guo
 * @date 2021/8/10 14:13
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        List<byte[]> list = new ArrayList<>();
        while (true) {
            byte[] b = new byte[1024 * 1024 * 10];
            list.add(b);
            System.out.println(new Date());
            Thread.sleep(1000 * 1);

        }
    }
}
