package com.dtguai;

import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Objects;

/**
 * 描述
 *
 * @author guo
 * @date 2022/4/24 14:10
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        cx cx1 = new cx("cx1", 1, 1);
        cx cx2 = new cx("cx2", 1, 1);
        cx cx3 = new cx("cx3", 3, 3);
        cx cx4 = new cx("cx3", 3, 3);
        HashSet set = new HashSet();
        set.add(cx1);
        set.add(cx2);
        set.add(cx3);
        set.add(cx1);
        set.add(cx2);
        set.add(cx3);
        set.add(cx4);
        System.out.println("set.size():" + set.size());


        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        ThreadLocal<Integer> threadLocal1 = new ThreadLocal<>();

        new Thread(() -> {

            System.out.println(threadLocal.get());
            System.out.println(threadLocal.get());

            threadLocal.set(1);
            threadLocal.set(2);
            threadLocal1.set(11);

            System.out.println(threadLocal.get());
            System.out.println(threadLocal1.get());
        }).start();

        new Thread(() -> {
            threadLocal.set(2);
            threadLocal1.set(22);

            System.out.println(threadLocal.get());
            System.out.println(threadLocal1.get());
        }).start();


        Thread.sleep(5000);
    }

}

@AllArgsConstructor
class cx {
    private String name;
    private int age;
    private int sex;

    /**
     * 默认 对象相等 就是引用同一个
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        System.out.println(o);
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        cx cx = (cx) o;
        return age == cx.age && sex == cx.sex && Objects.equals(name, cx.name);
    }

    /**
     * 默认对象内存地址
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = Objects.hash(name, age, sex);
        System.out.println(hash);
        return hash;
    }
}