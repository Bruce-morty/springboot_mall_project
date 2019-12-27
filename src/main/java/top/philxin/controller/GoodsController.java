package top.philxin.controller;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.philxin.model.Storage;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.model.responseModel.CommonsModel.BaseDataVo;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.GoodsService;

import javax.validation.Valid;

/**
 * @ClassName: GoodsController
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/26 0026 20:26
 * @version: v1.0
 */
@RestController
@RequestMapping("admin")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @RequestMapping("goods/list")
    public BaseRespVo getGoodsList(PageHelperVo pageHelperVo, String goodsSn, String name) {
        BaseDataVo baseDataVo = goodsService.queryGoods(pageHelperVo, goodsSn, name);
        return BaseRespVo.success(baseDataVo);
    }

    @RequestMapping("storage/create")
    public BaseRespVo uploadImage(MultipartFile file) {
        Storage storage = goodsService.uploadImage(file);
        return BaseRespVo.success(storage);
    }
}
