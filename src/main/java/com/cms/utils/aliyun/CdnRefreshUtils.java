package com.cms.utils.aliyun;

import com.aliyun.api.DefaultAliyunClient;
import com.aliyun.api.cdn.cdn20141111.request.RefreshObjectCachesRequest;
import com.aliyun.api.cdn.cdn20141111.response.RefreshObjectCachesResponse;
import com.taobao.api.ApiException;

/**
 * 方法描述: CDN
 *
 * author 小刘同学、
 * version v1.0
 * date 2018/1/2 16:13
 */
public class CdnRefreshUtils {

    private static String ACCESS_ID = "";
    private static String accessKey = "";
    private static final String SERVER_URL = "http://cdn.aliyuncs.com";

    public CdnRefreshUtils() {
    }

    public static void main(String[] args) throws ApiException {
        System.out.println(refreshPath("http://cdn.iwangame.net/js/test.html"));
    }

    public static boolean refreshPath(String path) {
        try {
            DefaultAliyunClient defaultAliyunClient = new DefaultAliyunClient("http://cdn.aliyuncs.com", ACCESS_ID, accessKey);
            RefreshObjectCachesRequest request = new RefreshObjectCachesRequest();
            request.setObjectPath(path);
            RefreshObjectCachesResponse response = (RefreshObjectCachesResponse)defaultAliyunClient.execute(request);
            return response.isSuccess();
        } catch (Throwable var4) {
            return false;
        }
    }

    public static void setACCESS_ID(String aCCESS_ID) {
        ACCESS_ID = aCCESS_ID;
    }

    public static void setAccessKey(String accessKey) {
        accessKey = accessKey;
    }

}
