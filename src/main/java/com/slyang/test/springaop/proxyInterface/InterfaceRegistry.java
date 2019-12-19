package com.slyang.test.springaop.proxyInterface;

import org.apache.ibatis.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;

public class InterfaceRegistry {

    private final Map<Class<?>, InterfaceProxyFactory<?>> knownInterfaces = new HashMap<>();


    public <T> T  getInterfaceProxy(Class<T> classT){
        return knownInterfaces.get(classT).getProxyInstance(classT);
    }

    public <T> void registryInterfaceProxy(Class<T> classT){

        if(knownInterfaces.containsKey(classT)){
            //
        }else{
            knownInterfaces.put(classT,new InterfaceProxyFactory(classT));
        }
    }

}
