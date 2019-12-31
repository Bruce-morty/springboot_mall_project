package top.philxin.util;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import top.philxin.model.Storage;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Component
public class FileUploadUtils {
    public static Storage processUploadFile(MultipartFile file) {
        Storage storage = new Storage();
        String fileName = file.getOriginalFilename();
        // 设置初始名
        storage.setName(fileName);
        String prefix = UUID.randomUUID().toString();
        fileName = prefix + "-" + fileName;
        // key,储存在数据库中的名字
        storage.setKey(fileName);
        // 设置类型名
        storage.setType(file.getContentType());
        // 设置大小
        storage.setSize(file.getSize());
        // 设置时间
        Date date = new Date();
        storage.setAddTime(date);
        storage.setUpdateTime(date);
        // 获取filename的hashCode
        int hashCode = fileName.hashCode();
        // 转成16进制
        String hexString = Integer.toHexString(hashCode);
        char[] chars = hexString.toCharArray();
        String base = "";
        for(char aChar : chars) {
            base = base + "/" + aChar;
        }
        fileName ="img/" + base + fileName;
        // 获取out目录路径
        File path = null;
        try {
             path = new File(ResourceUtils.getURL("classpath:").getPath().replace("%20"," ").replace('/', '\\'));
            System.out.println(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        File fileTo = new File(path,"static/" + fileName);
        if(!fileTo.getParentFile().exists()) {
            fileTo.getParentFile().mkdirs();
        }
        storage.setUrl("http://localhost:8080/" + fileName);
        try {
            file.transferTo(fileTo);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        // 图片上传后 默认是删除状态，提交后自行更改状态
        storage.setDeleted(false);
        return storage;
    }
}
