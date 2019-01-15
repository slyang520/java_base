package com.slyang.test.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HelloJava8 {

    @Test
    public void helloLambda(){
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        // @FunctionalInterface
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        new HelloJava8().execComparator2((Comparator2) () -> {
        });


    }

    public void execComparator2(Comparator2 comparator2) {
    }

    public void execComparator3(Comparator3 comparator3) {
        comparator3.hello2(123);
    }

    @FunctionalInterface
    public interface Comparator2 {
        void hello();
    }

    public interface Comparator3 {
        void hello();
        void hello2(int param);
    }

 }
