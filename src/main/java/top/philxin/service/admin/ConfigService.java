package top.philxin.service.admin;

import top.philxin.model.ConfigModel.ConfigExpressVo;
import top.philxin.model.ConfigModel.ConfigMallVo;
import top.philxin.model.ConfigModel.ConfigOrderVo;
import top.philxin.model.ConfigModel.ConfigWxVo;

/**
 * @author xqs
 * @version 1.0
 * @description
 * @date 2019/12/26 20:07
 */
public interface ConfigService {
    //获取商城配置信息
    ConfigMallVo queryByMall();

    //更改商城配置信息
    void updateMallConfig(ConfigMallVo configMallVo);

    //获取运费配置信息
    ConfigExpressVo queryByExpress();

    //更改运费配置信息
    void updateExpressConfig(ConfigExpressVo configExpressVo);

    //获取订单配置信息
    ConfigOrderVo queryByOrder();

    //更改订单配置信息
    void updateOrderConfig(ConfigOrderVo configOrderVo);

    //获取小程序配置信息
    ConfigWxVo queryByWx();

    //更改小程序配置信息
    void updateWxConfig(ConfigWxVo configWxVo);
}
