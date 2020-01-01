package top.philxin.service.wx;

import top.philxin.model.WxGoodsModel.WxGoodsDetailVo;
import top.philxin.model.WxGoodsModel.WxGoodsListVo;
import top.philxin.model.requestModel.CommonsModel.WxPageHelperVo;

public interface WxGoodsService {
    WxGoodsDetailVo getGoodsDetailInfo(Integer goodsId);

    WxGoodsListVo getGoodsList(WxPageHelperVo wxPageHelperVo, Integer brandId, Integer categoryId, String keyword);
}
