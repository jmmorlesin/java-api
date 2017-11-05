package com.jmms;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jmms"})
@EnableCaching
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class}) //To receive the proper http codes
public class JavaAPIApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new JavaAPIApplication()
                .configure(new SpringApplicationBuilder(JavaAPIApplication.class))
                .run(args);
    }
}
