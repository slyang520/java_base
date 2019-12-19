package com.slyang.test.loader.classLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;

public class HotswapClassLoader extends ClassLoader{

    private String basePath;
    private HashSet<String> loadedClass;  // 用来记录被这个类加载器所加载的类

    public HotswapClassLoader(String basePath, String[] classList) {
        // 跳过父类加载器，把它设为null
       // super(null);
        this.basePath = basePath;
        loadedClass = new HashSet<>();
        // 该类加载器在初始化的时候会直接把应该它负责加载的类加载好，
        // 这样之后 loadClass 时，会在第一步检验该类是否已经被加载时发现该类已经被加载过了，
        // 就无需执行 loadClass 之后的流程，直接返回虚拟机中被加载好的类即可，
        // 这样虽然初始化的时间长了点，但是之后 loadClass 时会比较省时间
        loadClassByMe(classList);
    }

    /**
     * 加载给定的的 classList 中的类到虚拟机
     */
    private void loadClassByMe(String[] classList) {
        for (int i = 0; i < classList.length; i++) {
            Class cls = loadClassDirectly(classList[i]);
            if (cls != null) {
                loadedClass.add(classList[i]);
            }
        }
    }

    /**
     * 通过文件名直接加载类，得到Class对象
     */
    private Class loadClassDirectly(String className) {
        Class cls = null;
        StringBuilder sb = new StringBuilder(basePath);
        String classPath = className.replace(".", File.separator) + ".class";
        //String classPath = className + ".class";

        sb.append(File.separator + classPath);
        File file = new File(sb.toString());
        InputStream fin = null;
        try {
            fin = new FileInputStream(file);
            // 将字节流转化成内存中的Class对象
            cls = instantiateClass(className, fin, (int) file.length());
            return cls;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 将字节流转化成内存中的Class对象啊，使用defineClass方法！
     */
    private Class instantiateClass(String name, InputStream fin, int len) {
        byte[] buffer = new byte[len];
        try {
            fin.read(buffer);
            return defineClass(name, buffer, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 覆盖原有的loadClass规则，
     */
    public Class loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class cls = null;
        // 应该由 HotswapClassLoader 负责加载的类会通过下面这一行得到类的 Class 对象，
        // 因为早在 HotswapClassLoader 类加载器执行构造函数时，它们就被加载好了
        cls = findLoadedClass(name);
        // 只有在这个类没有被加载，且！这个类不是当前这个类加载器负责加载的时候，才去使用启动类加载器
        if (cls == null && !loadedClass.contains(name)) {
            cls = findSystemClass(name);
        }
        if (cls == null) {
            throw new ClassNotFoundException(name);
        }
        // resolveClass是进行连接操作的，即"验证+准备+解析"，之后就可以进行初始化了
        if (resolve) {
            resolveClass(cls);
        }
        return cls;
    }


}
