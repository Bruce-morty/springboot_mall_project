package top.philxin.service.admin;

import top.philxin.model.Coupon;

import top.philxin.model.GeneralizeModel.GrouponActivities;
import top.philxin.model.Goods;
import top.philxin.model.GrouponRules;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;

import java.util.List;
import java.util.Map;

public interface Generalize_grouponService {
  Map queryGroupon(PageHelperVo pageHelperVo, Integer goodsId);

    Goods selectGoodsIs(GrouponRules grouponRules);

    int updateGroupon(GrouponRules grouponRules);

    GrouponRules insertGroupon(GrouponRules grouponRules);

    int  deleteGroupon(GrouponRules grouponRules);

    Map queryOrderGoodsGrouponRule(PageHelperVo pageHelperVo, Integer goodsId);
}
