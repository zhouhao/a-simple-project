package me.hzhou.todo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateModel;

@Configuration
public class AppConfiguration {

    private final freemarker.template.Configuration ftlConf;

    @Autowired
    public AppConfiguration(freemarker.template.Configuration ftlConf) {
        this.ftlConf = ftlConf;
    }

    @Bean
    public CommandLineRunner freemarkerInit() {
        return args -> {
            BeansWrapper wrapper = (BeansWrapper) ftlConf.getObjectWrapper();
            TemplateModel statics = wrapper.getStaticModels();
            TemplateModel enums = wrapper.getEnumModels();
            ftlConf.setSharedVariable("statics", statics);
            ftlConf.setSharedVariable("enums", enums);
        };
    }
}
