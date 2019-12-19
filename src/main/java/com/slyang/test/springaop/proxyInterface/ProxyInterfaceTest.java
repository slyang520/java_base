package com.slyang.test.springaop.proxyInterface;

import org.apache.ibatis.mapping.MappedStatement;

public class ProxyInterfaceTest {


    public static void main(String[] arg) {

        InterfaceRegistry interfaceRegistry = new InterfaceRegistry();
        interfaceRegistry.registryInterfaceProxy(InterfaceInstance.class);

        InterfaceInstance interfaceInstance = interfaceRegistry.getInterfaceProxy(InterfaceInstance.class);

        String hello= interfaceInstance.hello();
        System.out.println(hello);


        String hello2= interfaceInstance.hello2("fdfdfdaffdaf");
        System.out.println(hello2);


    }

}
