package com.soyoung.photo.util.thy;

import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.expression.IExpressionObjectFactory;

import java.util.HashSet;
import java.util.Set;

public class UtilsObjectFactory implements IExpressionObjectFactory {

    /**
     * 自定义内置对象名称
     * @return
     */
    private static final String EXPRESSION_OBJECT_NAME="frmUtils";

    /**
     * 返回表达式对象名称
     * @return
     */
    @Override
    public Set<String> getAllExpressionObjectNames() {
        Set<String> names=new HashSet<>();
        names.add(EXPRESSION_OBJECT_NAME);
        return names;
    }

    /**
     * 创建内置对象的应用实例
     * @param iExpressionContext
     * @param s
     * @return
     */
    @Override
    public Object buildObject(IExpressionContext iExpressionContext, String s) {
        if(EXPRESSION_OBJECT_NAME.equals(s)){
            return new FreeUtil();
        }
        return null;
    }

    /**
     * 是否缓存该对象
     * @param s
     * @return
     */
    @Override
    public boolean isCacheable(String s) {
        return false;
    }
}
