package top.philxin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//增加mapperscan的注解以扫描mapper
@SpringBootApplication
@MapperScan("top.philxin.mapper")
public class MallprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallprojectApplication.class, args);
    }

}
