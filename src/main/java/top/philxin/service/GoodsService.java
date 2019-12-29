package top.philxin.service;

import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.model.responseModel.CommonsModel.BaseDataVo;
import top.philxin.model.GoodsModel.CatAndBrandVo;
import top.philxin.model.GoodsModel.GoodsInfoDetailVo;

/**
 * @ClassName: GoodsService
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/26 0026 21:09
 * @version: v1.0
 */
public interface GoodsService {
    BaseDataVo queryGoods(PageHelperVo pageHelperVo, String goodsSn, String name);

    GoodsInfoDetailVo getGoodDetail(Integer goodsId);

    CatAndBrandVo getCatAndBrand();

    void updateGoodsDetailInfo(GoodsInfoDetailVo goodsInfoDetailVo);

    void createNewGoods(GoodsInfoDetailVo goodsInfoDetailVo);
}
