package com.pengjunlee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan(basePackages = {"com.pengjunlee.service.mapper"}) //扫描DAO
public class PinkPhotoApplication extends SpringBootServletInitializer {

    // 重写configure方法，否则在部署到tomcat时，接口将访问不到
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PinkPhotoApplication.class);
    }

    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(PinkPhotoApplication.class);
        application.run(args);


    }

}
