package com.github.io.shortvideodroplogo.network.json;//package com.paisheng.ijiami.util.json;
//
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONException;
//import com.alibaba.fastjson.JSONObject;
//import com.paisheng.ijiami.StringUtils;
//
//
//import java.util.List;
//
///**
// * <br> ClassName:   FJsonEngine
// * <br> Description: fastjson解析引擎
// * <br>
// * <br> Author:      yangyinglong
// * <br> Date:        2017/8/3 11:21
// */
//
//public class FJsonEngine implements IJsonParser{
//    /**
//     * Json转Object
//     * <p>
//     * 如：UserInfo
//     *
//     *      UserInfo userInfo = JsonParseUtil.fromJson(dataStr, UserInfo.class);
//     *
//     * @param dataStr String
//     */
//    @Override
//    public <T> T fromJson(String dataStr, Class<T> cls) {
//        if (StringUtils.isEmpty(dataStr)) {
//            return null;
//        }
//        /*T t = getSingleton().fromJson(dataStr, cls);*/
//
//        try {
//            T t = JSON.parseObject(dataStr, cls);
//            return t;
//        } catch (Exception e1) {
//            try {
//                Class c = Class.forName(cls.getName());
//                return (T) (c.newInstance());
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            }
//        }
//
//        return null;
//    }
//    /**
//     * Json转Object
//     * <p>
//     * 如：UserInfo
//     *
//     *      UserInfo userInfo = JsonParseUtil.fromJson(dataStr, UserInfo.class);
//     *
//     * 只在 GSON 中适用
//     *
//     * @param jsonElement JsonElement
//     */
//    /*public static <T> T fromJson(Object jsonElement, Class<T> cls) {
//        if (jsonElement instanceof String) {
//            return fromJson(jsonElement, cls);
//        }
//        if (jsonElement instanceof JsonElement && !((JsonElement)jsonElement).isJsonNull()) {
//            T t = getSingleton().fromJson((JsonElement)jsonElement, cls);
//            return t;
//        }
//
//        return fromJson("", cls);
//    }*/
//
//    /**
//     * Json转泛型Object
//     * <p>
//     * 如：HttpResult<UserInfo>
//     *
//     *     HttpResult<UserInfo> tmp
//     *          = JsonParseUtil.parseToGenericObject(dataStr, new TypeReference<HttpResult<UserInfo>>(){})
//     */
////    public static <T> T parseToGenericObject(String dataStr, GenericType<T> genericType) {
////        if (TextUtils.isEmpty(dataStr)) {
////            return null;
////        }
////        /*T t = getSingleton().fromJson(dataStr, genericType.getType());*/
////        try {
////            T t = JSON.parseObject(dataStr, genericType.getType());
////            return t;
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////        return null;
////    }
//
//
//
//    /**
//     * Object转Json String
//     * <p>
//     * 如：UserInfo
//     */
//    @Override
//    public String toJson(Object object) {
//        /*return (object == null) ? "" : getSingleton().toJson(object);*/
//        return (object == null) ? "" : JSON.toJSONString(object);
//    }
//
//
//
//    /**
//     * Json转单纯的List （dataStr为一个完整Json数组）
//     * <p>
//     * 如：[{"code":1,"name":"小米"},{"code":2,"name":"大米"}]  -->  List<GoodInfo>
//     */
//    public static <T> List<T> parseToPureList(String dataStr, Class<T> cls) {
//        if (StringUtils.isEmpty(dataStr)) {
//            return null;
//        }
//        /*T[] array = getSingleton().fromJson(dataStr, cls);
//        return new ArrayList<>(Arrays.asList(array));*/
//
//        try {
//            List<T> list = JSON.parseArray(dataStr, cls);
//            return list;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//
//
//    /**
//     * Json转指定Class数组 内涵不确定字段名Json数组
//     * <p>
//     * 如： { "XXX":[{"Id":1352}] }  -->  List<YYY>
//     */
//    public static <T> List<T> parseToDynamicList(String dataStr, String fieldName, Class<T> cls) {
//
//        String listData = "";
//        if (!StringUtils.isEmpty(dataStr)) {
//            try {
//                JSONObject jsonObj = new JSONObject(dataStr);
//                listData = jsonObj.optString(fieldName);
//            } catch (JSONException exp) {
//
//            }
//        }
//        return parseToPureList(listData, cls);
//    }
//
//    /**
//     * Json转指定Class 内涵不确定字段名Json对象
//     * <p>
//     * 如： { "XXX":{"Id":1352} }  -->  YYY
//     */
//    public <T> T parseToDynamicObject(String dataStr, String fieldName, Class<T> cls) {
//
//        String objectData = "";
//        if (!TextUtils.isEmpty(dataStr)) {
//            try {
//                JSONObject jsonObj = new JSONObject(dataStr);
//                objectData = jsonObj.optString(fieldName);
//            } catch (JSONException exp) {
//
//            }
//        }
//        return fromJson(objectData, cls);
//    }
//
//
//    public static String parseToString(String dataStr, String fieldName) {
//        if (!TextUtils.isEmpty(dataStr)) {
//            try {
//                JSONObject jsonObj = new JSONObject(dataStr);
//                return jsonObj.optString(fieldName);
//            } catch (JSONException exp) {
//
//            }
//        }
//        return "";
//    }
//
//    public static <T> String parseToString(List<T> list) {
//        if (list != null) {
//            try {
//                return JSON.toJSONString(list);
//            } catch (Exception exp) {
//
//            }
//        }
//        return "";
//    }
//
//    public static int parseToInt(String dataStr, String fieldName) {
//        if (!TextUtils.isEmpty(dataStr)) {
//            try {
//                JSONObject jsonObj = new JSONObject(dataStr);
//                return jsonObj.optInt(fieldName);
//            } catch (JSONException exp) {
//
//            }
//        }
//        return 0;
//    }
//}
