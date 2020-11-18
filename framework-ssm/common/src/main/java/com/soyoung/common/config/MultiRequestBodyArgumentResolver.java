package com.soyoung.common.config;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.soyoung.common.annotation.MultiRequestBody;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;


/**
 * 自定义参数解析器
 * 多@RequestBody解析器
 */
public class MultiRequestBodyArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String JSONBODY_ATTRIBUTE = "JSON_REQUEST_BODY";

    /**
     * 设置支持的方法参数类型
     *
     * @param methodParameter 方法参数
     * @return 支持的类型
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        // 支持带@MultiRequestBody注解的参数
        return methodParameter.hasParameterAnnotation(MultiRequestBody.class);
    }


    /**
     * 参数解析，利用fastjson
     * 注意：非基本类型返回null会报空指针异常，要通过反射或者JSON工具类创建一个空对象
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String jsonBody = getRequestBody(nativeWebRequest);
        JSONObject jsonObject = JSONUtil.parseObj(jsonBody);

        // 根据@MultiRequestBody注解value作为json解析的key
        MultiRequestBody parameterAnnotation = methodParameter.getParameterAnnotation(MultiRequestBody.class);
        //注解的value是JSON的key
        String key = parameterAnnotation.value();
        Object value;

        // 如果@MultiRequestBody注解没有设置value，则取参数名FrameworkServlet作为json解析的key
        if (!StrUtil.hasEmpty(key)) {
            value = jsonObject.get(key);
            // 如果设置了value但是解析不到，报错
            if (value == null && parameterAnnotation.required()) {
                throw new IllegalArgumentException(String.format("required param %s is not present", key));
            }
        } else {
            // 注解为设置value则用参数名当做json的key
            key = methodParameter.getParameterName();
            value = jsonObject.get(key);
        }

        // 获取的注解后的类型 Long
        Class<?> parameterType = methodParameter.getParameterType();
        // 通过注解的value或者参数名解析，能拿到value进行解析
        if (value != null) {
            //基本类型
            if (parameterType.isPrimitive()) {
                return parsePrimitive(parameterType.getName(), value);
            }
            // 基本类型包装类
            if (isBasicDataTypes(parameterType)) {
                return parseBasicTypeWrapper(parameterType, value);
                // 字符串类型
            } else if (parameterType == String.class) {
                return value.toString();
            }
            // 其他复杂对象
            return Convert.convert(parameterType,value.toString());
        }

        // 解析不到则将整个json串解析为当前参数类型
        if (isBasicDataTypes(parameterType)) {
            if (parameterAnnotation.required()) {
                throw new IllegalArgumentException(String.format("required param %s is not present", key));
            } else {
                return null;
            }
        }

        // 非基本类型，不允许解析所有字段，必备参数则报错，非必备参数则返回null
        if (!parameterAnnotation.parseAllFields()) {
            // 如果是必传参数抛异常
            if (parameterAnnotation.required()) {
                throw new IllegalArgumentException(String.format("required param %s is not present", key));
            }
            // 否则返回null
            return null;
        }
        // 非基本类型，允许解析，将外层属性解析
        Object result;
        try {
            result = Convert.convert(parameterType,jsonObject.toString());
        } catch (JSONException jsonException) {
            // TODO:: 异常处理返回null是否合理？
            result = null;
        }

        // 如果非必要参数直接返回，否则如果没有一个属性有值则报错
        if (!parameterAnnotation.required()) {
            return result;
        } else {
            boolean haveValue = false;
            Field[] declaredFields = parameterType.getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                if (field.get(result) != null) {
                    haveValue = true;
                    break;
                }
            }
            if (!haveValue) {
                throw new IllegalArgumentException(String.format("required param %s is not present", key));
            }
            return result;
        }
    }


    /**
     * 基本类型解析
     */
    private Object parsePrimitive(String parameterTypeName, Object value) {
        final String BOOLEAN_TYPE_NAME = "boolean";
        if (BOOLEAN_TYPE_NAME.equals(parameterTypeName)) {
            return Boolean.valueOf(value.toString());
        }
        final String INT_TYPE_NAME = "int";
        if (INT_TYPE_NAME.equals(parameterTypeName)) {
            return Integer.valueOf(value.toString());
        }
        final String CHAR_TYPE_NAME = "char";
        if (CHAR_TYPE_NAME.equals(parameterTypeName)) {
            return value.toString().charAt(0);
        }
        final String SHORT_TYPE_NAME = "short";
        if (SHORT_TYPE_NAME.equals(parameterTypeName)) {
            return Short.valueOf(value.toString());
        }
        final String LONG_TYPE_NAME = "long";
        if (LONG_TYPE_NAME.equals(parameterTypeName)) {
            return Long.valueOf(value.toString());
        }
        final String FLOAT_TYPE_NAME = "float";
        if (FLOAT_TYPE_NAME.equals(parameterTypeName)) {
            return Float.valueOf(value.toString());
        }
        final String DOUBLE_TYPE_NAME = "double";
        if (DOUBLE_TYPE_NAME.equals(parameterTypeName)) {
            return Double.valueOf(value.toString());
        }
        final String BYTE_TYPE_NAME = "byte";
        if (BYTE_TYPE_NAME.equals(parameterTypeName)) {
            return Byte.valueOf(value.toString());
        }
        return null;
    }


    /**
     * 基本类型包装类解析
     */
    private Object parseBasicTypeWrapper(Class<?> parameterType, Object value) {
        if (Number.class.isAssignableFrom(parameterType)) {
            Number number = (Number) value;
            if (parameterType == Integer.class) {
                return number.intValue();
            } else if (parameterType == Short.class) {
                return number.shortValue();
            } else if (parameterType == Long.class) {
                return number.longValue();
            } else if (parameterType == Float.class) {
                return number.floatValue();
            } else if (parameterType == Double.class) {
                return number.doubleValue();
            } else if (parameterType == Byte.class) {
                return number.byteValue();
            }
        } else if (parameterType == Boolean.class) {
            return value.toString();
        } else if (parameterType == Character.class) {
            return value.toString().charAt(0);
        }
        return null;
    }


    /**
     * 判断是否为基本数据类型包装类
     */
    private boolean isBasicDataTypes(Class clazz) {
        Set<Class> classSet = new HashSet<>();
        classSet.add(Integer.class);
        classSet.add(Long.class);
        classSet.add(Short.class);
        classSet.add(Float.class);
        classSet.add(Double.class);
        classSet.add(Boolean.class);
        classSet.add(Byte.class);
        classSet.add(Character.class);
        return classSet.contains(clazz);
    }


    /**
     * 获取请求体JSON字符串
     */
    private String getRequestBody(NativeWebRequest webRequest) {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);

        // 有就直接获取
        String jsonBody = (String) webRequest.getAttribute(JSONBODY_ATTRIBUTE, NativeWebRequest.SCOPE_REQUEST);
        // 没有就从请求中读取
        if (jsonBody == null) {
            try {
                jsonBody = IoUtil.read(servletRequest.getReader());
                webRequest.setAttribute(JSONBODY_ATTRIBUTE, jsonBody, NativeWebRequest.SCOPE_REQUEST);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return jsonBody;
    }
}

