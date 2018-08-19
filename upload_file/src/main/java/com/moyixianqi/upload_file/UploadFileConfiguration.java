package com.moyixianqi.upload_file;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@Configuration
public class UploadFileConfiguration {

//    @Bean
//    public MultipartConfigElement multipartConfigElement(){
//        MultipartConfigFactory factory=new MultipartConfigFactory();
//        factory.setMaxFileSize("256KB");
//        factory.setMaxRequestSize("512KB");
//        return factory.createMultipartConfig();
//    }
}
