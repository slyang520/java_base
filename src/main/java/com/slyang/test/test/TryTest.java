package com.slyang.test.test;

import org.junit.Test;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Description:
 * Created by slyang <slyang520@yeah.net>
 * Copyright (c) 2019, All Rights Reserved.
 */
public class TryTest {

    @Test
    public void originT(){

        StaticMethodMatcherPointcut staticMethodMatcherPointcut;

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("test"));
            System.out.println(inputStream.read());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        }
    }

    // try with resource
    @Test
    public void originJAVA7(){

        // case 1

        try (FileInputStream inputStream = new FileInputStream(new File("test"))){
            System.out.println(inputStream.read());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        // case 2

        try (FileInputStream inputStream = new FileInputStream(new File("test"));
             FileInputStream inputStream2 = new FileInputStream(new File("test2"))){
            System.out.println(inputStream.read());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }


    @Test
    public void testNull(){
        hello(null);
    }


    @Nullable
    @Nonnull 
    @CheckForNull
    static void hello(@Nonnull Integer id){
        System.out.println("hello");
    }


}
