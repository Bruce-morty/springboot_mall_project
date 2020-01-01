package top.philxin.service.wx;

import top.philxin.model.WxGoodsModel.WxGoodsDetailVo;

public interface WxGoodsService {
    WxGoodsDetailVo getGoodsDetailInfo(Integer goodsId);
}
