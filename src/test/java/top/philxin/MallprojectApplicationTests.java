package top.philxin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

@SpringBootTest
class MallprojectApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    public void test() {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath().replace("%20"," ").replace('/', '\\'));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(path);
    }

}
