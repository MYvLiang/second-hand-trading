package com.second.hand.trading.server.utils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author myl
 * @create 2020-12-25  14:25
 */
public class IdFactoryUtil {

    private static AtomicInteger orderIdEnd=new AtomicInteger(1);
    private static AtomicInteger fileIdEnd=new AtomicInteger(1);

    public static String getOrderId(){
        int newI;
        int ord;
        do{
            ord=orderIdEnd.get();
            newI=(ord+1)%10000;
        }
        while (!orderIdEnd.compareAndSet(ord,newI));
        return System.currentTimeMillis()+""+(newI+10000);
    }

    public static String getFileId(){
        int newI;
        int ord;
        do{
            ord=fileIdEnd.get();
            newI=(ord+1)%1000;
        }
        while (!fileIdEnd.compareAndSet(ord,newI));
        return System.currentTimeMillis()+""+(newI+1000);
    }
}
