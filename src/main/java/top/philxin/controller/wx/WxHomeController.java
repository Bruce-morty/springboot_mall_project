package top.philxin.controller.wx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.WxHomeService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("wx")
public class WxHomeController {
    @Autowired
    WxHomeService homeService;
    /**
     * 此方法用于小程序首页的显示
     * @return
     */
    @RequestMapping("home/index")
    public BaseRespVo getHomeIndex(){
        Map map = homeService.homeIndex();
        return BaseRespVo.success(map);
    }

    /**
     * 此方法用于小程序首页的显示,统计共有多少件商品
     * @return
     */
    @RequestMapping("goods/count")
    public BaseRespVo countGoods(){
        Integer count = homeService.countGoods();
        Map map = new HashMap();
        map.put("goodsCount",count);
        return BaseRespVo.success(map);
    }
}
