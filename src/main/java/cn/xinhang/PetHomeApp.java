package cn.xinhang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.xinhang.*.mapper")
public class PetHomeApp {
    public static void main(String[] args) {
        SpringApplication.run(PetHomeApp.class,args);
    }
}
