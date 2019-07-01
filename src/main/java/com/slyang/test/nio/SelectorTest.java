package com.slyang.test.nio;

import java.io.IOException;
import java.nio.channels.Selector;

public class SelectorTest {

    // http://ifeve.com/selectors/

    public static void main(String[] args) throws IOException {

        // 创建选择器
        Selector selector = Selector.open();
        // 注册通道 在选择器
//
//        channel.configureBlocking(false);
//        SelectionKey key = channel.register(selector, SelectionKey.OP_READ);

    }



}
