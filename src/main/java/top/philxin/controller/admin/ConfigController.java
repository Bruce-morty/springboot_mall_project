package top.philxin.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.ConfigModel.ConfigExpressVo;
import top.philxin.model.ConfigModel.ConfigMallVo;
import top.philxin.model.ConfigModel.ConfigOrderVo;
import top.philxin.model.ConfigModel.ConfigWxVo;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.ConfigService;

/**
 * @author xqs
 * @version 1.0
 * @description
 * @date 2019/12/26 17:40
 */
@RestController
@RequestMapping("admin/config")
public class ConfigController {

    @Autowired
    ConfigService configService;

    //获取商城信息
    @RequestMapping(value="mall",method = RequestMethod.GET)
    public BaseRespVo getConfigMall(){
        ConfigMallVo configMallVo = configService.queryByMall();
        return BaseRespVo.success(configMallVo);
    }
    //更改商城信息
    @RequestMapping(value = "mall",method = RequestMethod.POST)
    public BaseRespVo changeConfigMall(@RequestBody ConfigMallVo configMallVo){
        configService.updateMallConfig(configMallVo);
        return BaseRespVo.success();
    }
    //获取运费信息
    @RequestMapping(value="express",method = RequestMethod.GET)
    public BaseRespVo getConfigExpress(){
        ConfigExpressVo configExpressVo = configService.queryByExpress();
        return BaseRespVo.success(configExpressVo);
    }
    //更改运费信息
    @RequestMapping(value = "express",method = RequestMethod.POST)
    public BaseRespVo changeConfigExpress(@RequestBody ConfigExpressVo configExpressVo){
        configService.updateExpressConfig(configExpressVo);
        return BaseRespVo.success();
    }

    //获取订单配置信息
    @RequestMapping(value="order",method = RequestMethod.GET)
    public BaseRespVo getConfigOrder(){
        ConfigOrderVo configOrderVo = configService.queryByOrder();
        return BaseRespVo.success(configOrderVo);
    }

    //更改订单配置信息
    @RequestMapping(value="order",method = RequestMethod.POST)
    public BaseRespVo changeConfigOrder(@RequestBody ConfigOrderVo configOrderVo){
        configService.updateOrderConfig(configOrderVo);
        return BaseRespVo.success();
    }

    //获取小程序配置信息
    @RequestMapping(value="wx",method = RequestMethod.GET)
    public BaseRespVo getConfigWx(){
        ConfigWxVo configWxVo = configService.queryByWx();
        return BaseRespVo.success(configWxVo);
    }

    //更改小程序配置信息
    @RequestMapping(value="wx",method = RequestMethod.POST)
    public BaseRespVo changeConfigWx(@RequestBody ConfigWxVo configWxVo){
        configService.updateWxConfig(configWxVo);
        return BaseRespVo.success();
    }

}
