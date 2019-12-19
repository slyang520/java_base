package com.slyang.test.loader.classLoader;

import java.lang.reflect.Method;

public class TestApp {

    public static void main(String[] args){

        // AppClassLoader
        System.out.println(Thread.currentThread().getContextClassLoader().getClass().getName());

        String basePath = "/Users/slyang/openSourceProject/java/java_base/log";
        // 每执行一次任务都 new 一个新的类加载器
        HotswapClassLoader cl = new HotswapClassLoader(
                basePath, new String[]{"com.slyang.test.loader.classLoader.Foo"});
        try {
            // 通过我们自己实现的类加载器加载 Foo 类
            Class cls = cl.loadClass("com.slyang.test.loader.classLoader.Foo", true);
            Object foo = cls.newInstance();
            Method method = cls.getMethod("sayHello", new Class[]{});
            method.invoke(foo, new Object[]{});


            Foo foo1 = new Foo();
            foo1.sayHello();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
