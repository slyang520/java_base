package com.slyang.test.springaop.proxyInterface;

import org.apache.ibatis.reflection.ExceptionUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class InterfaceProxyFactory<T> {

    private final Class<T> mapperInterface;

    public InterfaceProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxyInstance(Class<T> type) {
        return (T) Proxy.newProxyInstance(this.mapperInterface.getClassLoader(), new Class[]{this.mapperInterface}, new SimpleInterfaceProxy(type));
    }


    class SimpleInterfaceProxy<T> implements InvocationHandler{

        private final Class<T> mapperInterface;

        public SimpleInterfaceProxy(Class<T> mapperInterface) {
            this.mapperInterface = mapperInterface;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (Object.class.equals(method.getDeclaringClass())) {
                try {
                    return method.invoke(this, args);
                } catch (Throwable var5) {
                    throw ExceptionUtil.unwrapThrowable(var5);
                }
            } else {
                Object result;

                // exec function
                return "hello"+ Arrays.toString(args);
            }
        }
    }



}

