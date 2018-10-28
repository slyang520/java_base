package com.slyang.test.loader;

import org.junit.Test;

import java.util.ServiceLoader;

public class HelloLoader {

    /**
     * Java spi
     * 类加载器
     * ServiceLoader
     * 运用场景（实现动态插件）
     * https://tech.meili-inc.com/serviceloader-106
     */
    @Test
    public void ServiceLoaderT() {
        ServiceLoader<IService> serviceLoader = ServiceLoader.load(IService.class);
        for (IService service : serviceLoader) {
            System.out.println(service.getScheme() + "=" + service.sayHello());

        }
    }

    /**
     * Java spi
     * 类加载器
     * ClassLoader
     * https://www.ibm.com/developerworks/cn/java/j-lo-classloader/index.html
     *
     *     顾名思义，类加载器（class loader）
     *     用来加载 Java 类到 Java 虚拟机中。
     *     一般来说，Java 虚拟机使用 Java 类的方式如下：Java 源程序（.java 文件）
     *     在经过 Java 编译器编译之后就被转换成 Java 字节代码（.class 文件）。
     *     类加载器负责读取 Java 字节代码，
     *     并转换成 java.lang.Class类的一个实例。
     *     每个这样的实例用来表示一个 Java 类。
     *     通过此实例的 newInstance()方法就可以创建出该类的一个对象。
     *     实际的情况可能更加复杂，
     *     ### 比如 Java 字节代码可能是通过工具动态生成的，也可能是通过网络下载的。###
     *
     *     TODO
     */
    @Test
    public void ClassLoaderT() {

        ClassLoader loader = HelloLoader.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader.toString());
            loader = loader.getParent();
        }

    }


}
