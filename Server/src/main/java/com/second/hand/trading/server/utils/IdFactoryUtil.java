package com.second.hand.trading.server.utils;

/**
 * @author myl
 * @create 2020-12-25  14:25
 */
public class IdFactoryUtil {

    private static int orderIdEnd=1;
    private static int fileIdEnd=1;

    public static synchronized String getOrderId(){
        orderIdEnd=(orderIdEnd+1)%10000;
        return System.currentTimeMillis()+""+(orderIdEnd+10000);
    }

    public static synchronized String getFileId(){
        fileIdEnd=(fileIdEnd+1)%1000;
        return System.currentTimeMillis()+""+(fileIdEnd+1000);
    }
}
