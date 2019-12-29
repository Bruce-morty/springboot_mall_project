package top.philxin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.annotation.LogRecordAnno;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.model.responseModel.CommonsModel.BaseDataVo;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.model.GoodsModel.CatAndBrandVo;
import top.philxin.model.GoodsModel.GoodsInfoDetailVo;
import top.philxin.service.GoodsService;

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

    /**
     * 获取规格和品牌信息
     * @return
     */
    @RequestMapping("goods/catAndBrand")
    public BaseRespVo getCatAndBrand() {
        CatAndBrandVo catAndBrandVo = goodsService.getCatAndBrand();
        return BaseRespVo.success(catAndBrandVo);
    }

    /**
     * 更新商品信息
     * @param goodsInfoDetailVo
     * @return
     */
    @RequestMapping("goods/update")
    public BaseRespVo updateGoodsDetailInfo(@RequestBody GoodsInfoDetailVo goodsInfoDetailVo) {
        goodsService.updateGoodsDetailInfo(goodsInfoDetailVo);
        return BaseRespVo.success();
    }

    /**
     * 新增商品
     * @param goodsInfoDetailVo
     * @return
     */
    @LogRecordAnno(operateAction = "新增商品")
    @RequestMapping("goods/create")
    public BaseRespVo createGoods(@RequestBody GoodsInfoDetailVo goodsInfoDetailVo) {
        goodsService.createNewGoods(goodsInfoDetailVo);
        String goodsSn = goodsInfoDetailVo.getGoods().getGoodsSn();
        String errmsg = "商品编号:" + goodsSn;
        return BaseRespVo.success(errmsg);
    }


}
