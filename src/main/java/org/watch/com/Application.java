package org.watch.com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.watch.com.util.resultJson.ResponseResult;

/**
 * Spring Boot 应用启动类
 * <p>
 * Created by bysocket on 16/4/26.
 */
@SpringBootApplication
@MapperScan("org.sbbzb.com.mapper")
public class Application {
//        extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // war
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder
//                                                         builder) {
//        // TODO Auto-generated method stub
//        return builder.sources(Application.class);
//    }

}
