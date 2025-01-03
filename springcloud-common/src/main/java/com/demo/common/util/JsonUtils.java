package com.demo.common.util;

import com.demo.common.domain.Response;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * json序列化工具类
 * @author classgeng
 */
public class JsonUtils {

    /**
     * 私有构造方法
     */
    private JsonUtils(){}

    /**
     * 全局Gson
     */
    private static final Gson GSON = new Gson();

    /**
     * 转化为JSON
     * @param object 需要处理的对象
     */
    public static String toJson(Object object){
        return GSON.toJson(object);
    }

    /**
     * 转化为对象
     * @param json   原Json数据
     * @param tClass 转化为的Json对象
     * @param <T>    对象类型
     */
    public static <T> T fromJson(String json,Class<T> tClass){
        return GSON.fromJson(json, tClass);
    }

    /**
     * 转化为对象
     * @param json   原Json数据
     * @param tClass 转化为的Json对象
     * @param <T>    对象类型
     */
    public static <T> T fromJson(JsonElement json, Class<T> tClass){
        return GSON.fromJson(json, tClass);
    }


    /**
     * json反序列化
     * @param json json数据
     */
    public static <T> Response<T> fromJsonToObj(String json, Type type){
        return new Gson().fromJson(json,type);
    }


    /**
     * json反序列化
     * @param json json数据
     * @param type 类型
     * @param <T>  返回的数据
     */
    public static <T> List<T> fromJson(String json, Type type){
        // Type type = new TypeToken<List<UserInfo>>() {}.getType();
        return GSON.fromJson(json,type);
    }

    /**
     * json反序列化
     * @param json json数据
     * @param tClass 类型
     * @param <T>  返回的数据
     */
    public static <T> List<T> toList(String json, Class<T> tClass){
        return GSON.fromJson(json, new ListOfJson<>(tClass));
    }

    static class ListOfJson<T> implements ParameterizedType {
        private Class<?> wrapped;

        public ListOfJson(Class<T> wrapper){
            this.wrapped = wrapper;
        }
        @Override
        public Type[] getActualTypeArguments() {
            return new Type[] { wrapped };
        }
        @Override
        public Type getRawType() {
            return List.class;
        }
        @Override
        public Type getOwnerType() {
            return null;
        }
    }

}
