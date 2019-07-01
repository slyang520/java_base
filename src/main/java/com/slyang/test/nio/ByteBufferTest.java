package com.slyang.test.nio;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;

public class ByteBufferTest {

    // http://tutorials.jenkov.com/java-nio/scatter-gather.html

//    Channel, 对非阻塞的支持, Channel可以连接文件，socket等
//            Buffer, 类似数组, 可以直接由Channnel读写, DirectByteBuffer可以分配堆外内存
//    Selector, 就是上面的Reactor, 告知哪些Channel上发生了I/O事件
//    SelectionKey, 代表I/O事件状态和绑定


    public static void main(String[] args) throws IOException {

        RandomAccessFile aFile = new RandomAccessFile("/Users/slyang/openSourceProject/java/java_base/src/main/resources/spring-tr-3.xml", "rw");
        FileChannel inChannel = aFile.getChannel();   // 通道
        ByteBuffer buf = ByteBuffer.allocate(48);     // 缓冲区

        int bytesRead;
        while ( ( bytesRead  = inChannel.read(buf) ) != -1) {

           buf.flip();
            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }
            buf.clear();

            System.out.println();
            System.out.println("-------------------"+bytesRead);

        }

        aFile.close();

    }


//    scatter（分散）是指数据从一个channel读取到多个buffer中。

    @Test
    public void scatteringReads(){

        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body = ByteBuffer.allocate(1024);

        
        ByteBuffer[] bufferArray = { header, body };

//        channel.read(bufferArray);

//        read()方法按照buffer在数组中的顺序将从channel中读取的数据写入到buffer，
//        当一个buffer被写满后，channel紧接着向另一个buffer中写。channel.read(bufferArray);

    }

//    gather（聚集）是指多个buffer的数据写入到同一个channel。
    @Test
    public void gatheringWrites(){

        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body= ByteBuffer.allocate(1024);
        ByteBuffer[] bufferArray = { header, body };
//        channel.write(bufferArray);

    }

    @Test
    public void selector(){
        // jdk中Selector是对操作系统的IO多路复用调用的一个封装，在Linux中就是对epoll的封装
        Selector selector;

    }


}
