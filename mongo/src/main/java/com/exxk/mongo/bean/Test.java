package com.exxk.mongo.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

public class Test {

    public static void main(String[] args) {
        int[] a=new int[]{1,2,4,5,6,3,5,8,6};
        int[] b= sort(a);
        for (int i = 0; i < b.length; i++) {
            System.out.println(b[i]);
        }
    }

    private static int[]  sort(int[] a){
        int left=0;
        int right=a.length-1;
        int tmp=-1;
        for (int i = 0; i < a.length; i++) {
            if (left>right){
                break;
            }
            if (tmp==-1) {
                if (a[left] % 2 == 0) {
                    tmp = left;
                }
                left=left+1;
            }
            if (tmp!=-1){
                if (a[right]%2!=0){
                    int t=a[right];
                    a[right]=a[tmp];
                    a[tmp]=t;
                    tmp=-1;
                }
                right=right-1;
            }
        }
        return a;
    }




    private void count(int[] a, int target){
//        int[] a = new int[0];
//        int target = 0;
        Map<Integer,Integer> temp=new HashMap<Integer,Integer>();
        for (int i = 0; i < a.length; i++) {
           if (i<target){
               int sub= target-a[i];
               if (temp.get(sub)!=null){
                   System.out.println("第"+i+"+第"+temp.get(sub).intValue()+"的数据和为"+target);
               }
               temp.put(sub,i);

           }
        }
        

        
    }

}
