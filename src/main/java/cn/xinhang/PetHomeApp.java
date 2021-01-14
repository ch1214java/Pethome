package cn.xinhang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@MapperScan("cn.xinhang.*.mapper")
@PropertySource("classpath:email.properties")
public class PetHomeApp {
    public static void main(String[] args) {
        SpringApplication.run(PetHomeApp.class,args);
    }
}
