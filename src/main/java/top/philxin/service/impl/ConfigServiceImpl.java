package top.philxin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.SystemMapper;
import top.philxin.mapper.config_mapper.ConfigMapper;
import top.philxin.model.ConfigModel.ConfigExpressVo;
import top.philxin.model.ConfigModel.ConfigMallVo;
import top.philxin.model.ConfigModel.ConfigOrderVo;
import top.philxin.model.ConfigModel.ConfigWxVo;
import top.philxin.model.SystemExample;
import top.philxin.service.ConfigService;

/**
 * @author xqs
 * @version 1.0
 * @description
 * @date 2019/12/26 20:08
 */
@Service
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    ConfigMapper configMapper;


    /**
     * 获得商城配置
     * @return
     */
    @Override
    public ConfigMallVo queryByMall() {
        int phoneId = 12;
        int addressId = 14;
        int qqId = 8;
        int nameId = 6;
        String qq = configMapper.selectByQQ(qqId);
        String address = configMapper.selectByAddress(addressId);
        String name = configMapper.selectByName(nameId);
        String phone = configMapper.selectByPhone(phoneId);
        ConfigMallVo configMallVo = new ConfigMallVo(phone,address,name,qq);
        return configMallVo;
    }

    /**
     * 更改商城信息
     * @param configMallVo
     */
    @Override
    public void updateMallConfig(ConfigMallVo configMallVo) {
        int phoneId = 12;
        int addressId = 14;
        int qqId = 8;
        int nameId = 6;
        configMapper.updatePhone(phoneId,configMallVo);
        configMapper.updateAddress(addressId,configMallVo);
        configMapper.updateQQ(qqId,configMallVo);
        configMapper.updateName(nameId,configMallVo);
    }

    /**
     * 获得运费配置信息
     * @return
     */
    @Override
    public ConfigExpressVo queryByExpress() {
        int minId = 5;
        int valueId =7;
        String min = configMapper.selectExpressMinById(minId);
        String value = configMapper.selectExpressValueById(valueId);
        ConfigExpressVo configExpressVo = new ConfigExpressVo(min, value);
        return configExpressVo;
    }

    /**
     * 更改运费配置信息
     * @param configExpressVo
     */
    @Override
    public void updateExpressConfig(ConfigExpressVo configExpressVo) {
        int minId = 5;
        int valueId =7;
        configMapper.updateExpressMin(minId,configExpressVo);
        configMapper.updateExpressValue(valueId,configExpressVo);
    }

    @Override
    public ConfigOrderVo queryByOrder() {
        int commentId = 10;
        int unpaidId = 1;
        int unconfirmId = 3;
        String comment = configMapper.selectOrderComment(commentId);
        String unconfirm = configMapper.selectOrderUnconfirm(unconfirmId);
        String unpaid = configMapper.selectOrderUnpaid(unpaidId);
        ConfigOrderVo configOrderVo = new ConfigOrderVo(comment, unpaid, unconfirm);
        return configOrderVo;
    }

    @Override
    public void updateOrderConfig(ConfigOrderVo configOrderVo) {
        int commentId = 10;
        int unpaidId = 1;
        int unconfirmId = 3;
        configMapper.updateOrderComment(commentId,configOrderVo);
        configMapper.updateOrderUnconfirm(unconfirmId,configOrderVo);
        configMapper.updateOrderUnpaid(unpaidId,configOrderVo);
    }

    @Override
    public ConfigWxVo queryByWx() {
        int newId = 2;
        int goodsId = 11;
        int listId = 13;
        int shareId = 4;
        int brandId = 15;
        int hotId = 9;
        int topicId = 16;
        String new_value = configMapper.selectWxnew(newId);
        String goods_value = configMapper.selectWxgoods(goodsId);
        String list_value = configMapper.selectWxlist(listId);
        String share_value = configMapper.selectWxshare(shareId);
        String brand_value = configMapper.selectWxbrand(brandId);
        String hot_value = configMapper.selectWxhot(hotId);
        String topic_value = configMapper.selectWxtopic(topicId);
        ConfigWxVo configWxVo = new ConfigWxVo(new_value, goods_value, list_value, share_value, brand_value, hot_value, topic_value);
        return configWxVo;
    }

    @Override
    public void updateWxConfig(ConfigWxVo configWxVo) {
        int newId = 2;
        int goodsId = 11;
        int listId = 13;
        int shareId = 4;
        int brandId = 15;
        int hotId = 9;
        int topicId = 16;
        configMapper.updateWxnew(newId,configWxVo);
        configMapper.updateWxgoods(goodsId,configWxVo);
        configMapper.updateWxlist(listId,configWxVo);
        configMapper.updateWxshare(shareId,configWxVo);
        configMapper.updateWxbrand(brandId,configWxVo);
        configMapper.updateWxhot(hotId,configWxVo);
        configMapper.updateWxtopic(topicId,configWxVo);
    }
}
