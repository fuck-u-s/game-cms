package com.cms.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static String replaceAll(String str, String regex, String val) {
        Pattern p = Pattern.compile(regex);//建立模式对象
        Matcher m = p.matcher(str);
        return m.replaceAll(val);
    }

    public static String numToChinese(Integer number) {
        String string = number.toString();

        StringBuilder result = new StringBuilder();
        int len = string.length();
        int count = 0;
        while (len > 0)    //依次取出最后，以4位字符为标准取子串
        {
            int i = (len - 4) >= 0 ? (len - 4) : 0;
            String temp = string.substring(i, len);
            System.out.println(temp);
            String res = processTemp(temp, count);  // 对这四个字符进行处理
            result.insert(0, res);              //往前将求得的字符串插入sb中
            len -= 4;
            count++;  // 记录这个子串中能组成多少个 4个字符的串

        }

        return result.toString();
    }

    public static String processTemp(String temp, int count)
    {
        String[] hanArr = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        String[] unitArr = { "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };
        String res = "";
        int len1 = temp.length();
        // 0042
        // 200
        for (int i = 0; i < len1; i++)
        {
            //将每个字符转成数字
            int num = temp.charAt(i) - 48;
            if (num == 0)  //如果是0
            {
                if (i == (len1 - 1))  //到了子串的最后一个字符
                {
                    continue;
                }
                //如果下一个是数字字符不是0
                if (temp.charAt(i + 1) != '0')
                {
                    res += hanArr[num];
                }
                else {
                    continue;
                }
            }
            else if( i == (len1 - 1) ) {  //不是0,最后一个字符
                res += hanArr[num];
            }
            else  //
            {
                res += hanArr[num] + unitArr[len1 - i - 2];
            }
        }

        if (count == 1)
        {
            res += "万";
        }
        else if (count == 2)
        {
            res += "亿";
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(numToChinese(648));
    }
}
