package com.soyoung.photo.util.thy;

import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

public class UtilExpDialect implements IExpressionObjectDialect {
    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        return new UtilsObjectFactory();
    }

    @Override
    public String getName() {
        return "DateUtils Dialect";
    }
}
