package com.slyang.test;

import com.slyang.test.juc.disruptor.DisruptorTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;
import static org.mockito.Mockito.*;

/**
 * Description:
 * Created by slyang <slyang520@yeah.net>
 * Copyright (c) 2019, All Rights Reserved.
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

    @Mock
    public List<String> fooMock;

    @Test
    public void hello(){

        fooMock.clear();
        verify(fooMock).clear();
        
        // mock creation
        List<String> mockedList =(List<String>)mock(List.class);
        // using mock object - it does not throw any "unexpected interaction" exception
        mockedList.add("one");
        mockedList.clear();
        // selective, explicit, highly readable verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    @Test
    public void hello2(){
        // you can mock concrete classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);

        // stubbing appears before the actual execution
        when(mockedList.get(0)).thenReturn("first");

        // the following prints "first"
        System.out.println(mockedList.get(0));

        // the following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));
    }

    @Test
    public void hello3(){
        DisruptorTest disruptorTest = new DisruptorTest();

        disruptorTest.measureCAS();

    }


}
