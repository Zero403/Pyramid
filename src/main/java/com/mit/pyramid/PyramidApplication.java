package com.mit.pyramid;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.mit.pyramid.dao")
public class PyramidApplication {

    public static void main(String[] args) {
        SpringApplication.run(PyramidApplication.class, args);
    }

}
