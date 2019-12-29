package top.philxin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//增加mapperscan的注解以扫描mapper,开启事务
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("top.philxin.mapper")
public class MallprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallprojectApplication.class, args);
    }

}
