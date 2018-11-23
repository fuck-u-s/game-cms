package com.cms.utils;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * json，java对象互相转换类
 *
 * @author
 */
public class JsonUtil {

    private static String JSONUTILE_ISNULL = "(:null,|:NULL,)";
    private static String JSONUTILE_REPLACE = ":\"\",";
    private static String JSONUTILE_JSONNULL = "{}";

    private static Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private static ObjectMapper objectMapper = null;
    private static JsonUtil json = null;

    /**
     * 实例化JsonUtil
     *
     * @author
     */
    @SuppressWarnings("unused")
    private static JsonUtil instance() {
        if (json == null) {
            json = new JsonUtil();
        }
        return json;
    }

    /**
     * 将Object对象转为JSON字符串
     *
     * @param obj
     *            JavaBean/Map/List<?> 等 例：User user， 实体User的对象 Map<String,Object> map; map对象 List<Object> list;list对象 List<Map<String,Object>> listmap;listmap对象
     * @author
     */
    public static String ObjectToJson(Object obj) {
        if (obj == null) {
            return JSONUTILE_JSONNULL;
        }
        try {
            objectMapper = new ObjectMapper();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            objectMapper.setDateFormat(sdf);

			/*objectMapper.configure(Feature.WRITE_NUMBERS_AS_STRINGS, true);
			objectMapper.configure(Feature.QUOTE_NON_NUMERIC_NUMBERS, true);
			objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
				@Override
				public void serialize(Object value, JsonGenerator jgen,
						SerializerProvider provider) throws IOException,
						JsonProcessingException {
					jgen.writeString("");
				}
            });*/

            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.debug("Object转换json字符串错误!", e);
        }
        ;
        return JSONUTILE_JSONNULL;
    }

    /**
     * 将JSON字符串转换为Object对象
     *
     * @param json
     *            需要转换的json字符串;
     * @param clas
     *            需要转换成的类型 例：List.class; User.class(实体类)
     * @author
     */
    public static <T> T JsonToObject(String json, Class<T> clas) {
        if (json == null) {
            return null;
        }
        try {
            objectMapper = new ObjectMapper();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            objectMapper.setDateFormat(sdf);
            return objectMapper.readValue(json, clas);
        } catch (IOException e) {
            log.debug("json转换Object错误!", e);
        }
        return null;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static List<Map<String, Object>> readJson2ListMap(String json) {
        try {
            objectMapper = new ObjectMapper();
            List objs = objectMapper.readValue(json, List.class);
            List<Map<String, Object>> maps = new ArrayList<>();
            for (Object str : objs) {
                if (str instanceof String) {
                    Map<String, Object> temp = objectMapper.readValue(str.toString(), Map.class);
                    maps.add(temp);
                }
            }
            if (!objs.isEmpty() && maps.isEmpty()) {
                return objs;
            }
            return maps;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static List<String> readJson2ListStr(String json) {
        try {
            objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> readJson2Map(String json) {
        try {
            objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将JSON格式中的null值转换成""串
     *
     * @author
     */
    @SuppressWarnings("unused")
    private static String convertNullToEmpty(String json) {
        return json.replaceAll(JSONUTILE_ISNULL, JSONUTILE_REPLACE);
    }

    /**
     * 设置时间格式 setDateFormate(DateUtil.ddd);
     *
     * @param dateFormt
     * @author
     */
    public JsonUtil setDateFormate(String dateFormt) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormt);
        objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(sdf);
        return this;
    }


}
