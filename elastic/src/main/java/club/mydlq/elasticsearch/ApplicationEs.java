package club.mydlq.elasticsearch;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableKnife4j
@EnableSwagger2
@SpringBootApplication
public class ApplicationEs {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationEs.class, args);
    }

}
