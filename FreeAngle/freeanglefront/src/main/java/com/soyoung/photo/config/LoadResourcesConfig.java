package com.soyoung.photo.config;
import com.soyoung.photo.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;

/**
 * @author faith.huan 2019-10-20 9:27
 */
@Component
public class LoadResourcesConfig {
    private ServletContext context;
    private DictionaryService dictionaryService;

    /**
     * spring版本大于等于4.3可以不加 @Autowired
     */
    @Autowired
    public LoadResourcesConfig(@Value("${spring.application.name}") String name, ServletContext context,DictionaryService dictionaryService) {
        context.setAttribute("dict",dictionaryService.getTypeList());
        context.setAttribute("httpURL", name);
    }
}
