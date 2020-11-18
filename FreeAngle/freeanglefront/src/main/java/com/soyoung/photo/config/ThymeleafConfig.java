package com.soyoung.photo.config;

import com.soyoung.photo.util.thy.UtilExpDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresource.ITemplateResource;

import java.util.Collection;
import java.util.Collections;

@Configuration
public class ThymeleafConfig {

    @Autowired
    private Collection<ITemplateResolver> templateResources= Collections.emptySet();

    @Autowired(required = false)
    private Collection<IDialect> dialects=Collections.emptySet();

    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine engine=new SpringTemplateEngine();
        for (ITemplateResolver templateResource:this.templateResources){
            engine.addTemplateResolver(templateResource);
        }
        for (IDialect dialect:this.dialects){
            engine.addDialect(dialect);
        }
        engine.addDialect(new UtilExpDialect());
        return engine;
    }
}
