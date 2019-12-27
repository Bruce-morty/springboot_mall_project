package top.philxin.mapper.config_mapper;

import org.apache.ibatis.annotations.Param;
import top.philxin.model.ConfigModel.ConfigExpressVo;
import top.philxin.model.ConfigModel.ConfigMallVo;
import top.philxin.model.ConfigModel.ConfigOrderVo;
import top.philxin.model.ConfigModel.ConfigWxVo;
import top.philxin.model.System;

/**
 * @author xqs
 * @version 1.0
 * @description
 * @date 2019/12/26 19:55
 */
public interface ConfigMapper {
    //配置管理：商城配置
    String selectByPhone(int phoneId);

    String selectByName(int nameId);

    String selectByQQ(int qqId);

    String selectByAddress(int addressId);

    //更改商城信息
    int updatePhone(@Param("phoneId") int phoneId, @Param("configMallVo") ConfigMallVo configMallVo);

    int updateName(@Param("nameId") int nameId, @Param("configMallVo") ConfigMallVo configMallVo);

    int updateQQ(@Param("qqId") int qqId, @Param("configMallVo") ConfigMallVo configMallVo);

    int updateAddress(@Param("addressId") int addressId, @Param("configMallVo") ConfigMallVo configMallVo);

    //获得运费信息
    String selectExpressMinById(@Param("minId") int minId);

    String selectExpressValueById(@Param("valueId") int valueId);

    //更改运费信息
    int updateExpressMin(@Param("minId") int minId, @Param("configExpressVo") ConfigExpressVo configExpressVo);

    int updateExpressValue(@Param("valueId") int valueId, @Param("configExpressVo") ConfigExpressVo configExpressVo);

    //获得订单配置信息
    String selectOrderComment(@Param("commentId") int commentId);

    String selectOrderUnpaid(@Param("unpaidId") int unpaidId);

    String selectOrderUnconfirm(@Param("unconfirmId") int unconfirmId);

    //更改订单配置信息
    int updateOrderComment(@Param("commentId") int commentId, @Param("configOrderVo") ConfigOrderVo configOrderVo);

    int updateOrderUnpaid(@Param("unpaidId") int unpaidId, @Param("configOrderVo") ConfigOrderVo configOrderVo);

    int updateOrderUnconfirm(@Param("unconfirmId") int unconfirmId, @Param("configOrderVo") ConfigOrderVo configOrderVo);

    //获取小程序配置信息
    String selectWxnew(@Param("newId") int newId);

    String selectWxgoods(@Param("goodsId") int goodsId);

    String selectWxlist(@Param("listId") int listId);

    String selectWxshare(@Param("shareId") int shareId);

    String selectWxbrand(@Param("brandId") int brandId);

    String selectWxhot(@Param("hotId") int hotId);

    String selectWxtopic(@Param("topicId") int topicId);

    //更改小程序配置信息
    int updateWxnew(@Param("newId") int newId, @Param("configWxVo") ConfigWxVo configWxVo);

    int updateWxgoods(@Param("goodsId") int goodsId, @Param("configWxVo") ConfigWxVo configWxVo);

    int updateWxlist(@Param("listId") int listId, @Param("configWxVo") ConfigWxVo configWxVo);

    int updateWxshare(@Param("shareId") int shareId, @Param("configWxVo") ConfigWxVo configWxVo);

    int updateWxbrand(@Param("brandId") int brandId, @Param("configWxVo") ConfigWxVo configWxVo);

    int updateWxhot(@Param("hotId") int hotId, @Param("configWxVo") ConfigWxVo configWxVo);

    int updateWxtopic(@Param("topicId") int topicId, @Param("configWxVo") ConfigWxVo configWxVo);
}
