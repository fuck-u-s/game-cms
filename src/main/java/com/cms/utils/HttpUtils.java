package com.cms.utils;

import com.xuan.utils.RandomUtils;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HttpUtils {


	//实例化Http请求
	static OkHttpClient client = new OkHttpClient();

	//定义JSON提交头
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	// GET方式请求
	public static String GET(String url) throws IOException {
		Request request = new Request.Builder().url(url)
            .addHeader("Cookie","pgv_pvid=4781524472; pgv_pvi=5117782016; ptui_loginuin=499866197; pt2gguin=o0499866197; RK=4ba10KJxFV; ptcz=ae26296cfdf99a93ec63962114c4fc08f1ddc53482917c5bdaf4a5531b02d633; tvfe_boss_uuid=cca3748670023cbd; o_cookie=499866197; pac_uid=1_499866197; luin=o0499866197; lskey=00010000e6307f74f10d6c048b786c843e3bf0840bb9cec3a82a13371d99bb54b698806d6ba0f899a786a939; kb_h5_user_id=KBH5UserId_15408673987163490; sd_userid=86761540884083303; sd_cookie_crttime=1540884083303; qv_als=Q7U0U3XZCYPwEcOSA11541037123xPQ0ng==; pgv_info=ssid=s8887302737")
            .addHeader("Content-Type","application/json; charset=utf-8")
            .addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
            .addHeader("Host","kuaibao.qq.com")
            .addHeader("Cache-Control","no-cache")
            .addHeader("X-Requested-With","XMLHttpRequest")
            .addHeader("Upgrade-Insecure-Requests","1")
            .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36")
            .addHeader("X-Real-IP","124.232.152.154")
            .addHeader("X-Forwarded-For","124.232.152.154")
            .build();

		Response response = client.newCall(request).execute();
		return response.body().string();
	}

	// POST提交JSON
	public static String POST2JSON(String url, String json) throws IOException {
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}

    /**
     * okHttp 提交Form
     */
    public static String post(String url,Map<String,Object> map) throws Exception {
        //遍历参数
        FormBody.Builder newFormBody = new FormBody.Builder();
        for(Map.Entry<String, Object> entry:map.entrySet()){
            newFormBody.add(entry.getKey(),entry.getValue()+"");
        }
        RequestBody formBody = newFormBody.build();

        Request request = new Request.Builder().url(url).post(formBody).build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            String result = response.body().string();
            return result;
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }


	public static String WXGET(String url,String key) throws IOException {
        int i = RandomUtils.getRandomInt(1,255);
        int y = RandomUtils.getRandomInt(1,255);
		FormBody.Builder newFormBody = new FormBody.Builder();
		newFormBody.add("openId",key);
		newFormBody.add("playerId","1683669");
		newFormBody.add("activityId","21443");
		RequestBody formBody = newFormBody.build();
		Request request = new Request.Builder().url(url)
                .addHeader("Cookie","JSESSIONID=AC2EEA82CE273DE56145AA4702BCF9A0")
                .addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Accept","application/json, text/javascript, */*; q=0.01")
                .addHeader("Host","web.longteng888.net")
                .addHeader("Cache-Control","no-cache")
                .addHeader("X-Requested-With","XMLHttpRequest")
                .addHeader("Upgrade-Insecure-Requests","1")
                .addHeader("User-Agent","MicroMessenger")
                .addHeader("X-Real-IP","124.232."+y+"."+i)
                .addHeader("X-Forwarded-For","124.232."+y+"."+i)
				.post(formBody)
				.build();

		Response response = client.newCall(request).execute();
		return response.body().string();
	}

    public static String WXGETS(String url) throws IOException {
        int i = RandomUtils.getRandomInt(1,255);
        int y = RandomUtils.getRandomInt(1,255);
        Request request = new Request.Builder().url(url)
                .addHeader("Cookie","JSESSIONID=AC2EEA82CE273DE56145AA4702BCF9A0")
                .addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Accept","application/json, text/javascript, */*; q=0.01")
                .addHeader("Host","web.longteng888.net")
                .addHeader("Cache-Control","no-cache")
                .addHeader("X-Requested-With","XMLHttpRequest")
                .addHeader("Upgrade-Insecure-Requests","1")
                .addHeader("User-Agent","MicroMessenger")
                .addHeader("X-Real-IP","124.232."+y+"."+i)
                .addHeader("X-Forwarded-For","124.232."+y+"."+i)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

	public static void main(String[] args)throws Exception {
        for(int i=0;i < 200;i++){
            String random = RandomUtils.getRandomStr(5);
            System.out.println(random);
            String key = "ozvbg1T9JUqvs31ojKjtgJ_"+random;
            String p = "http://web.longteng888.net/index/vote/success?activityId=21443&openId="+key+"&playerId=1683669&number=17&activityName=%E4%B9%98%E7%9D%80%E9%9F%B3%E4%B9%90%E4%B9%98%E7%9D%80%E9%9F%B3%E4%B9%90%E7%9A%84%E7%BF%85%E8%86%80%EF%BC%8C%E8%81%86%E5%90%AC%E7%81%B5%E9%AD%82%E7%9A%84%E5%A3%B0%E9%9F%B3%E2%80%94%E2%80%94%E5%AF%BB%E6%89%BE%E9%87%8D%E5%BA%86%E6%9C%80%E2%80%9C%E4%B8%93%E4%B8%9A%E2%80%9D%E9%9F%B3%E4%B9%90%E5%9F%B9%E8%AE%AD%E7%8F%AD%EF%BC%88E%E5%8C%BA%EF%BC%89";
            String res = WXGETS(p);
            //System.out.println(res);
            String a = res.substring(7353,7385);
            String b = res.substring(7414,7447);

            System.out.println("代码：A["+b+"],B["+a+"]");

            String url = "http://web.longteng888.net/index/"+b+"/"+a;
            String ress = WXGET(url,key);
            System.out.println("第"+(i+1)+"次："+ress);
            Thread.sleep(2000);
        }
	}
}
