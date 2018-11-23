package com.cms.utils.aliyun;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * 方法描述: Oss管理器
 * <p>
 * author 小刘同学、
 * version v1.0
 * date 2018/1/2 16:09
 */
public abstract class OssPuter {

    public static final String DATA_TIMECOUNT = "dataTimecount";
    public static final String RETRY2 = "retry";
    public static final String TCP_TIMEOUT = "tcpTimeout";
    public static final String MAX_CONN_COUNT = "maxConnCount";
    public static final String ENDPOINT = "endpoint";
    public static final String ACCESS_KEY_SECRET = "accessKeySecret";
    public static final String ACCESS_KEY_ID = "accessKeyId";
    public static final String OSS_PROPERTIES_FILE = "oss.properties";
    public static final String BUCKET_NAME = "icpa168";
    public static final String APK_TYPE = "application/octet-stream";
    public static final String HTML_APK_TYPE = "text/html";
    public static final String JS_APK_TYPE = "application/javascript";
    public static final String CSS_APK_TYPE = "text/css";
    public static final String JPEG_APK_TYPE = "image/jpeg";
    public static final String GIF_APK_TYPE = "image/gif";
    public static final String PNG_APK_TYPE = "image/png";
    private static Object lockObject = new Object();
    private static String accessKeyId = "n8sq8l6uxSs35Zte";
    private static String accessKeySecret = "E0GDiXRzZR4S2NGP9Rgky7kAawdQHS";
    private static String endpoint = "http://oss-cn-hangzhou-internal.aliyuncs.com";
    private static ClientConfiguration config;
    private static OSSClient client = null;
    private static int maxConnCount = 2000;
    private static int tcpTimeout = 5000;
    private static int retry = 3;
    private static int dataTimecount = 5000;

    public OssPuter() {
    }

    private static void init() {
        if (client == null) {
            Object var0 = lockObject;
            synchronized (lockObject) {
                if (client == null) {
                    InputStream resources = null;

                    try {
                        Properties p = new Properties();
                        resources = getResouce();
                        if (resources != null) {
                            p.load(resources);
                            if (p.containsKey("accessKeyId")) {
                                accessKeyId = p.getProperty("accessKeyId");
                                // 刷新CDN
                                // CdnRefreshUtils.setACCESS_ID(accessKeyId);
                            }

                            if (p.containsKey("accessKeySecret")) {
                                accessKeySecret = p.getProperty("accessKeySecret");
                                // 刷新CDN
                                // CdnRefreshUtils.setAccessKey(accessKeySecret);
                            }

                            if (p.containsKey("endpoint")) {
                                endpoint = p.getProperty("endpoint");
                            }

                            if (p.containsKey("maxConnCount")) {
                                maxConnCount = Integer.valueOf(p.getProperty("maxConnCount")).intValue();
                            }

                            if (p.containsKey("tcpTimeout")) {
                                tcpTimeout = Integer.valueOf(p.getProperty("tcpTimeout")).intValue();
                            }

                            if (p.containsKey("retry")) {
                                retry = Integer.valueOf(p.getProperty("retry")).intValue();
                            }

                            if (p.containsKey("dataTimecount")) {
                                dataTimecount = Integer.valueOf(p.getProperty("dataTimecount")).intValue();
                            }

                            resources.close();
                            resources = null;
                        }
                    } catch (IOException var12) {
                        var12.printStackTrace();
                    } finally {
                        if (resources != null) {
                            try {
                                resources.close();
                            } catch (IOException var11) {
                                var11.printStackTrace();
                            }
                        }

                    }

                    config = config(maxConnCount, tcpTimeout, retry, dataTimecount);
                    client = new OSSClient(endpoint, accessKeyId, accessKeySecret, config);
                }
            }
        }

    }

    private static InputStream getResouce() {
        try {
            return com.cms.utils.aliyun.OssPuter.class.getClassLoader().getResourceAsStream("oss.properties");
        } catch (Exception var1) {
            return null;
        }
    }

    private static ClientConfiguration config(int maxConnCount, int tcpTimeout, int retry, int dataTimecount) {
        ClientConfiguration conf = new ClientConfiguration();
        conf.setMaxConnections(maxConnCount);
        conf.setConnectionTimeout(tcpTimeout);
        conf.setMaxErrorRetry(retry);
        conf.setSocketTimeout(dataTimecount);
        return conf;
    }

    public static String putFile(String domain, String bucketName, String path, File file) {
        PutObjectResult putObject = putObject("application/octet-stream", bucketName, path, file, (InputStream) null, true);
        String string = putObject != null ? "http://" + domain + "/" + path : null;
//        if (string != null) {
//            CdnRefreshUtils.refreshPath(string);
//        }

        return string;
    }

    public static String putStream(String domain, String bucketName, String path, InputStream is) {
        PutObjectResult putObject = putObject("application/octet-stream", bucketName, path, (File) null, is, false);
        String string = putObject != null ? "http://" + domain + "/" + path : null;
//        if (string != null) {
//            CdnRefreshUtils.refreshPath(string);
//        }
        return string;
    }

    public static void deleteFile(String bucketName, String path) {
        init();
        String suffix = getSuffix(path);
        if (suffix != null) {
            client.deleteObject(bucketName, suffix);
        }
    }

    protected static String getSuffix(String path) {
        try {
            String string = path != null && path.contains("http") ? (new URL(path)).getPath() : path;
            return string != null && string.startsWith("/") ? string.substring(1) : string;
        } catch (MalformedURLException var2) {
            return path;
        }
    }

    private static PutObjectResult putObject(String fileType, String bucket, String path, File file, InputStream is, boolean isFile) {
        init();
        InputStream content = null;
        PutObjectResult result = null;

        try {
            if (file != null && isFile) {
                content = new FileInputStream(file);
            } else if (!isFile && is != null) {
                content = is;
            }

            if (content != null) {
                ObjectMetadata meta = new ObjectMetadata();
                int ava = ((InputStream) content).available();
                meta.setContentLength((long) ava);
                String ext = FilenameUtils.getExtension(path);
                if (!ext.equals("html") && !ext.equals("htm")) {
                    if (ext.equals("js")) {
                        meta.setContentType("application/javascript");
                    } else if (ext.equals("css")) {
                        meta.setContentType("text/css");
                    } else if (!ext.equals("jpeg") && !ext.equals("jpg")) {
                        if (ext.equals("gif")) {
                            meta.setContentType("image/gif");
                        } else if (ext.equals("png")) {
                            meta.setContentType("image/png");
                        } else {
                            meta.setContentType(fileType);
                        }
                    } else {
                        meta.setContentType("image/jpeg");
                    }
                } else {
                    meta.setContentType("text/html");
                }
                result = client.putObject(bucket, path, (InputStream) content, meta);
            }
        } catch (Throwable var18) {
            throw new RuntimeException(var18);
        } finally {
            if (content != null) {
                try {
                    ((InputStream) content).close();
                } catch (IOException var17) {
                    var17.printStackTrace();
                }
            }
        }
        return result;
    }
}
