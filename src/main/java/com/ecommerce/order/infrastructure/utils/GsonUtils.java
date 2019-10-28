package com.ecommerce.order.infrastructure.utils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import com.ecommerce.order.infrastructure.exception.ServiceException;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GsonUtils {
    private static Gson gson = new GsonBuilder().create();
    private static Gson gsonWithDate = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    /**
     * 简单的bean转为json
     */
    public static String toJson(Object object) {
        String json = null;
        if (gson != null) {
            json = gson.toJson(object);
        }
        return json;
    }

    public static String toJson(Object object, boolean isDateFormat) {
        if (isDateFormat) {
            return gsonWithDate.toJson(object);
        } else {
            return gson.toJson(object);
        }
    }

    /**
     * json转为简单的Bean
     * 使用默认的gson和gsonWithDate两种方式解析json
     * 若gson解析失败，则尝试gsonWithDate
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        try {
            return gson.fromJson(json, classOfT);
        } catch (Exception e) {
            // empty
        }

        try {
            return gsonWithDate.fromJson(json, classOfT);
        } catch (Exception e) {
            log.error("failed to parse json, json is : {}", json);
            throw new ServiceException("Error parsing JSON String", e);
        }
    }

    public static <T> T fromJson(String json, Class<T> classOfT, boolean isDateFormat) {
        try {
            T t;
            if (isDateFormat) {
                t = gsonWithDate.fromJson(json, classOfT);
            } else {
                t = gson.fromJson(json, classOfT);
            }
            return t;
        } catch (Exception e) {
            // empty
        }

        try {
            T t;
            if (!isDateFormat) {
                t = gsonWithDate.fromJson(json, classOfT);
            } else {
                t = gson.fromJson(json, classOfT);
            }
            return t;
        } catch (Exception e) {
            log.error("failed to parse json, json is : {}", json);
            throw new ServiceException("Error parsing JSON String", e);
        }

    }

    public static <T> T fromJson(String json, Type typeOfT) {
        try {
            return gson.fromJson(json, typeOfT);
        } catch (Exception e) {
            // empty
        }

        try {
            return gsonWithDate.fromJson(json, typeOfT);
        } catch (Exception e) {
            log.error("failed to parse json, json is : {}", json);
            throw new ServiceException("Error parsing JSON String", e);
        }

    }

    /**
     * json转为带泛型的List
     */
    public static <T> List<T> fromJsonList(String json, Class<T[]> cls) {
        T[] array;
        try {
            array = gson.fromJson(json, cls);
            return Lists.newArrayList(array);
        } catch (Exception e) {
            // empty
        }

        try {
            array = gsonWithDate.fromJson(json, cls);
            return Lists.newArrayList(array);
        } catch (Exception e) {
            log.error("failed to parse json, json is : {}", json);
            throw new ServiceException("Error parsing JSON String", e);
        }

    }

    public static <T> List<T> fromJsonList(String json, Class<T[]> cls, boolean isDateFormat) {
        T[] array;
        try {
            if (isDateFormat) {
                array = gsonWithDate.fromJson(json, cls);
            } else {
                array = gson.fromJson(json, cls);
            }
            return Lists.newArrayList(array);
        } catch (Exception e) {
            // empty
        }

        try {
            if (!isDateFormat) {
                array = gsonWithDate.fromJson(json, cls);
            } else {
                array = gson.fromJson(json, cls);
            }
            return Lists.newArrayList(array);
        } catch (Exception e) {
            log.error("failed to parse json, json is : {}", json);
            throw new ServiceException("Error parsing JSON String", e);
        }
    }

    /**
     * json转为带泛型的Set
     */
    public static <T> Set<T> fromJsonSet(String json, Class<T[]> cls) {
        T[] array;
        try {
            array = gson.fromJson(json, cls);
            return Sets.newHashSet(array);
        } catch (Exception e) {
            // empty
        }

        try {
            array = gsonWithDate.fromJson(json, cls);
            return Sets.newHashSet(array);
        } catch (Exception e) {
            log.error("failed to parse json, json is : {}", json);
            throw new ServiceException("Error parsing JSON String", e);
        }
    }

}
