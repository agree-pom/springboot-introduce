package cn.com.agree.afa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Spring Boot入门介绍项目
 *
 * @author mx
 */
@EnableFeignClients
@SpringBootApplication
public class AfaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AfaApplication.class, args);
    }

}
