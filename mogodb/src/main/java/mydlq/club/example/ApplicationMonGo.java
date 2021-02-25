package mydlq.club.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 启动类
 *
 * @author mydlq
 */
@EnableSwagger2
@SpringBootApplication
public class ApplicationMonGo {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMonGo.class, args);
    }

}