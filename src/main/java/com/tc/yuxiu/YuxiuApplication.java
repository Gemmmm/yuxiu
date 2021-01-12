package com.tc.yuxiu;

import com.tc.yuxiu.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.tc.yuxiu.dao")
public class YuxiuApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuxiuApplication.class, args);
        System.out.println("http://localhost:8081/swagger/index.html");
    }

}
