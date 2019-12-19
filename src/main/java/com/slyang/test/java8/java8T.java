package com.slyang.test.java8;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Description:
 * Created by slyang <slyang520@yeah.net>
 * Copyright (c) 2019, All Rights Reserved.
 */
public class java8T {

    @Test
    public void originT() {

        // 方法引用
        // String::toLowerCase  等同于     x->x.toLowerCase()

        // System.out::println  等同于     o -> System.out.println(o)

        // 构造器引用
        // ArrayList::new       等同于     () -> new ArrayList<>()

        List<String> collected = new ArrayList<>();
        collected.add("alpha");
        collected.add("beta");
        collected = collected.stream().
                map(String::toUpperCase).
                collect(Collectors.toCollection(ArrayList::new));

        // 1
        collected = collected.stream().
                map(String::toUpperCase).
                collect(Collectors.toCollection((Supplier<List<String>>) () -> new ArrayList<>()));

        // 2
        collected = collected.stream().
                map(String::toUpperCase).
                collect(Collectors.toCollection(() -> new ArrayList<>()));

        // 3
        collected = collected.stream().
                map(String::toUpperCase).
                collect(Collectors.toCollection(ArrayList::new));

        List<Integer> a =Arrays.asList(1,2);
        a.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer o) {
                 System.out.println(o);
            }
        });
        a.forEach(o -> System.out.println(o));
        a.forEach(System.out::println);

    }

    @Test
    public void originT2() {

        List<String> words = Arrays.asList(
                "hello",
                "world"
        );

        // 1.map
        //将一种类型的值转换为另外一种类型的值。

        // String 转为了 String[]
        // Stream<String>   =>  Stream<String[]>
        List<String[]> figure = words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(figure);

        // 2.flatMap
        // 将多个Stream连接成一个Stream，这时候不是用新值取代Stream的值，
        // 与map有所区别，这是重新生成一个Stream对象取而代之。
        List<String> figures = words.stream()
                .flatMap(word -> Stream.of(word.split("")))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(figures);

    }

    /**
     * 简单类型分组
     */
    @Test
    public void groupBy() {
        List<String> items = Arrays.asList(
                "apple", "apple",
                "orange", "orange", "orange",
                "blueberry",
                "peach", "peach", "peach", "peach"
        );

        // 分组
        Map<String, List<String>> result1 = items.stream().collect(Collectors.groupingBy(Function.identity()));

        // 分组，计数
        Map<String, Long> result = items.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println( JSON.toJSON(result) );

    }

    /**
     * 复杂类型分组
     */
    @Test
    public void groupBy2() {

        List<Item> items = Arrays.asList(
                new Item("apple", 10, new BigDecimal(23.5)),
                new Item("apple", 20, new BigDecimal(32.5)),
                new Item("orange", 30, new BigDecimal(13.9)),
                new Item("orange", 20, new BigDecimal(33.5)),
                new Item("orange", 10, new BigDecimal(63.5)),
                new Item("orange", 50, new BigDecimal(41.5)),
                new Item("peach", 20, new BigDecimal(26.5)),
                new Item("peach", 30, new BigDecimal(42.5)),
                new Item("peach", 40, new BigDecimal(24.5)),
                new Item("peach", 10, new BigDecimal(12.5))
        );

        // 分组，计数
        Map<String, Long> counting = items.stream()
                .collect(Collectors.groupingBy(Item::getName, Collectors.counting()));
        System.out.println( JSON.toJSON(counting) );

        
        // 分组，计数_数量
        Map<String, Integer> sum = items.stream()
                .collect(Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));
        System.out.println( JSON.toJSON(sum) );


        // 按价格分组
        Map<BigDecimal, List<Item>> groupByPriceMap = items.stream()
                .collect(Collectors.groupingBy(Item::getPrice));
        System.out.println( JSON.toJSON(groupByPriceMap) );

        // 按价格分组 转化List->Set    数据转化
        Map<BigDecimal, Set<String>> result = items.stream()
                .collect(Collectors.groupingBy(Item::getPrice, Collectors.mapping(Item::getName, Collectors.toSet())));
        System.out.println( JSON.toJSON(result) );

    }

    /**
     * 复杂类型分组 （多分组）
     */
    @Test
    public void groupBy3() {

        List<Item> items = Arrays.asList(
                new Item("apple", 10, new BigDecimal(23.5)),
                new Item("apple", 20, new BigDecimal(32.5)),
                new Item("orange", 30, new BigDecimal(13.9)),
                new Item("orange", 20, new BigDecimal(13.5)),
                new Item("orange", 10, new BigDecimal(13.5)),
                new Item("orange", 50, new BigDecimal(41.5)),
                new Item("peach", 20, new BigDecimal(26.5)),
                new Item("peach", 30, new BigDecimal(42.5)),
                new Item("peach", 40, new BigDecimal(12.5)),
                new Item("peach", 10, new BigDecimal(12.5))
        );

        Function<Item,String> fetchFunc = (item -> item.getName()+"#"+item.price.toPlainString());
        //Function<Item,String> fetchFunc2 = (item -> item.getName());
        Map<String, List<Item>> collect = items.stream().collect(Collectors.groupingBy(fetchFunc));
        System.out.println( JSON.toJSON(collect) );

    }


    public class Item {

        private String name;

        private int qty;

        private BigDecimal price;

        public Item() {
        }

        public Item(String name, int qty, BigDecimal price) {
            this.name = name;
            this.qty = qty;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "name='" + name + '\'' +
                    ", qty=" + qty +
                    ", price=" + price +
                    '}';
        }
    }
}
