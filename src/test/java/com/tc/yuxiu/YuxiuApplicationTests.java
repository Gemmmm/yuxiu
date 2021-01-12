package com.tc.yuxiu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootTest
class YuxiuApplicationTests {

    @Test
    void contextLoads() {

       /* File folder = new File("/static/upload");
        if (!folder.exists()) {
            folder.mkdirs();
            System.out.println("创建");
        }
        System.out.println("结束");

        ClassPathResource res3 = new ClassPathResource("/static");
        System.out.println(res3.getPath());
        File targetFile = new File(res3.getPath() + "test.png");
        if (!targetFile.exists()) {
            try {
                targetFile.createNewFile();
            } catch (IOException e) {
                System.out.println("错误");
            }
        }*/

        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(path.getAbsolutePath());
        File folder = new File(path.getAbsolutePath(),"/upload/");
        if (!folder.exists()) {
            folder.mkdirs();
            System.out.println("创建");
        }else{

            System.out.println("存在");
        }
    }



}
