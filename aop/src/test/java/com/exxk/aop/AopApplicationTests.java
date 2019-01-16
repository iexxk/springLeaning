package com.exxk.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AopApplicationTests {

    @Test
    public void contextLoads() {
        Integer numAobj=new Integer(10);
        Integer numBobj=new Integer(20);
        int numA=10;
        int numB=20;

        swap(numA,numB);
        swapObj(numAobj,numBobj);
        System.out.println("numA="+numA);
        System.out.println("numB="+numB);
        System.out.println("numAobj="+numAobj);
        System.out.println("numBobj="+numBobj);

    }

    public void swapObj(Integer a,Integer b){
        Integer temp=a;
        a=b;
        b=temp;
        System.out.println("aObj="+a);
        System.out.println("bObj="+b);
    }

    public void swap(int a,int b){
        int temp=a;
        a=b;
        b=temp;
        System.out.println("a="+a);
        System.out.println("b="+b);
    }
}

