package top.philxin.mapper;


import org.apache.ibatis.annotations.Param;
import top.philxin.model.GeneralizeModel.GrouponActivities;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;

import java.util.List;

public interface GrouponRulesforOrderMapper {

    List<GrouponActivities> queryOrderGoodsGrouponRule(@Param("pageHelperVo") PageHelperVo pageHelperVo, @Param("goodsId") Integer goodsId);

}
