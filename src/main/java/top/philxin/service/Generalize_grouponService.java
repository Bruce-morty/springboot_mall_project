package top.philxin.service;

import top.philxin.model.Coupon;

import top.philxin.model.Goods;
import top.philxin.model.GrouponRules;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;

import java.util.List;

public interface Generalize_grouponService {
    List<GrouponRules> queryGroupon(PageHelperVo pageHelperVo, Integer goodsId);

    Goods selectGoodsIs(GrouponRules grouponRules);

    int updateGroupon(GrouponRules grouponRules);

    GrouponRules insertGroupon(GrouponRules grouponRules);

    int  deleteGroupon(GrouponRules grouponRules);
}
