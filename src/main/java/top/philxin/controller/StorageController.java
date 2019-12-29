package top.philxin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.philxin.model.Storage;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.StorageService;

/**
 * @ClassName: StorageController
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/27 0027 17:07
 * @version: v1.0
 */
@RestController
@RequestMapping("admin")
public class StorageController {

    @Autowired
    StorageService storageService;

    /**
     * 上传图片
     * @param file
     * @return
     */
    @RequestMapping("storage/create")
    public BaseRespVo uploadImage(MultipartFile file) {
        Storage storage = storageService.uploadImage(file);
        return BaseRespVo.success(storage);
    }

}
