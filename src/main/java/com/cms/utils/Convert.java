package com.cms.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 转换方法
 * Created by lzh on 2015/7/7.
 */
public class Convert {
    public static int strToInt(String str, int defaultValue) {
        int Result = defaultValue;

        try {
            Result = Integer.parseInt(str);
        } catch (Exception e) {
        }

        return Result;
    }

    public static long strToLong(String str, long defaultValue) {
        long Result = defaultValue;

        try {
            Result = Long.parseLong(str);
        } catch (Exception e) {
        }

        return Result;
    }

    public static float strToFloat(String str, float defaultValue) {
        float Result = defaultValue;

        try {
            Result = Float.parseFloat(str);
        } catch (Exception e) {
        }

        return Result;
    }

    public static double strToDouble(String str, double defaultValue) {
        double Result = defaultValue;

        try {
            Result = Double.parseDouble(str);
        } catch (Exception e) {
        }

        return Result;
    }

    public static BigDecimal strToBigDecimal(String str,BigDecimal defaultValue){
        BigDecimal Result = defaultValue;
        try{
            BigDecimal big = new BigDecimal(str);
            Result = big;
        }catch (Exception e){
        }
        return Result;
    }

    public static boolean strToBoolean(String str, boolean defaultValue) {
        boolean Result = defaultValue;

        try {
            Result = Boolean.parseBoolean(str);
        } catch (Exception e) {
        }

        return Result;
    }

    public static Date strToDate(String str, Date defaultValue) {

        return strToDate(str, "yyyy-MM-dd HH:mm:ss", defaultValue);
    }

    public static Date strToDate(String str, String format, Date defaultValue) {
        Date Result = defaultValue;
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        try {
            Result = formatter.parse(str);
        } catch (Exception e) {
        }

        return Result;
    }

    public static String dateToStr(Date date, String defaultValue) {

        return dateToStr(date, "yyyy-MM-dd HH:mm:ss", defaultValue);
    }

    public static String dateToStr(Date date, String format, String defaultValue) {
        String Result = defaultValue;
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        try {
            Result = formatter.format(date);
        } catch (Exception e) {
        }

        return Result;
    }

    public static String strToStr(String str, String defaultValue) {
        String Result = defaultValue;

        if ((str != null) && (!str.isEmpty())) {
            Result = str;
        }

        return Result;
    }

    public static java.sql.Date dateToSqlDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static Date sqlDateToDate(java.sql.Date date) {
        return new Date (date.getTime());
    }

    public static java.sql.Timestamp dateToSqlTimestamp(Date date) {
        return new java.sql.Timestamp (date.getTime());
    }

    public static Date qlTimestampToDate(java.sql.Timestamp date) {
        return new Date (date.getTime());
    }

    /**
     * �ַ�תASC
     *
     * @param st
     * @return
     */
    public static int strtoAsc(String st) {
        byte[] gc = st.getBytes();
        int asnum = gc[0];
        return asnum;
    }

    /**
     * ASCת�ַ�
     *
     * @param backnum
     * @return
     */
    public static char intToChar(int backnum) {
        char stchar = (char) backnum;
        return stchar;
    }
}
