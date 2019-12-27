package top.philxin.service;

import org.springframework.web.multipart.MultipartFile;
import top.philxin.model.Storage;

/**
 * @ClassName: StorageService
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/27 0027 17:08
 * @version: v1.0
 */
public interface StorageService {
    Storage uploadImage(MultipartFile file);

    void ImageConditionUpdate(String imageUrl, boolean condition);
}
