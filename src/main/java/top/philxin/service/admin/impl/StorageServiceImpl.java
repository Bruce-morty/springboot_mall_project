package top.philxin.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.philxin.mapper.StorageMapper;
import top.philxin.model.Storage;
import top.philxin.service.admin.StorageService;
import top.philxin.util.FileUploadUtils;

/**
 * @ClassName: StorageServiceImpl
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/27 0027 17:09
 * @version: v1.0
 */
@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    StorageMapper storageMapper;

    /**
     * 图片上传
     * @param file
     * @return
     */
    @Override
    public Storage uploadImage(MultipartFile file) {
        Storage storage = FileUploadUtils.processUploadFile(file);
        int insert = storageMapper.insert(storage);
        if(insert != 1) {
            return null;
        }
        return storage;
    }

    /**
     * 更改图片状态
     * @param imageUrl 图片url
     * @param condition 设置该图片的状态 true：删除 false：提交
     * @return
     */
    @Override
    public void ImageConditionUpdate(String imageUrl, boolean condition) {
        storageMapper.updateDeletedCondition(imageUrl,condition);
    }
}
