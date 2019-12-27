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
import top.philxin.model.responseModel.GoodsModel.GoodsInfoDetailVo;
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

    /**
     * 获取商品列表
     * @param pageHelperVo
     * @param goodsSn
     * @param name
     * @return
     */
    @RequestMapping("goods/list")
    public BaseRespVo getGoodsList(PageHelperVo pageHelperVo, String goodsSn, String name) {
        BaseDataVo baseDataVo = goodsService.queryGoods(pageHelperVo, goodsSn, name);
        return BaseRespVo.success(baseDataVo);
    }

    /**
     * 获取商品详细信息
     * @param id
     * @return
     */
    @RequestMapping("goods/detail")
    public BaseRespVo getGoodsDetail(Integer id) {
        GoodsInfoDetailVo goodsInfoDetailVo = goodsService.getGoodDetail(id);
        return BaseRespVo.success(goodsInfoDetailVo);
    }
}
