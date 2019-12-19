package com.slyang.test.test;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.BitSet;

public class BitSetTest {

    // https://www.cnblogs.com/xupengzhang/p/7966755.html
    //全量bitset
    private static BitSet allBitSet = new BitSet();
    //偶数bitset
    private static BitSet evenBitSet = new BitSet();
    //奇数bitset
    private static BitSet oddBitSet = new BitSet();
    //空bitset
    private static BitSet emptyBitSet = new BitSet();


    @BeforeClass
    public static void init(){
           for (int i = 0; i < 5; i++) {
               allBitSet.set(i);
               if (i % 2 == 0) {
                   evenBitSet.set(i);
               }else{
                   oddBitSet.set(i);
               }
           }
    }


             //测试初始化
      @Test
      public void testInit(){
        //断点进去看
      }

      //测试基础的and\or\xor运算
        @Test
        public void testOper(){
        //System.out.println(evenBitSet.toByteArray());
        evenBitSet.and(allBitSet);
        System.out.println("偶数Bit and 全量Bit："+evenBitSet);
        evenBitSet.xor(allBitSet);
        System.out.println("偶数Bit xor 全量Bit："+evenBitSet);
        evenBitSet.or(allBitSet);
        System.out.println("偶数Bit or 全量Bit："+evenBitSet);
    }


    @Test
    public void testOper222(){
        System.out.println(1>>4);
        System.out.println(1<<4);

    }

}
