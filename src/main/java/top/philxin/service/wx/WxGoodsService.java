package top.philxin.service.wx;

import top.philxin.model.Category;
import top.philxin.model.WxGoodsModel.WxGoodsDetailVo;
import top.philxin.model.WxGoodsModel.WxGoodsListVo;
import top.philxin.model.requestModel.CommonsModel.WxPageHelperVo;

import java.util.List;

public interface WxGoodsService {
    WxGoodsDetailVo getGoodsDetailInfo(Integer goodsId);

    WxGoodsListVo getGoodsList(WxPageHelperVo wxPageHelperVo, Integer brandId, Integer categoryId, String keyword);

    Category getCurrentCategory(Integer id);

    List<Category> getBrotherCategory(Integer id);

}
