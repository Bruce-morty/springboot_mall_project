package top.philxin.controller.wx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.wx.WxSearchService;

import java.util.Map;

/**
 * @ClassName:
 * @Description: zy
 * @author:
 * @date:
 * @version: v1.0
 */
@RestController
@RequestMapping("wx")
public class WxSearchController {

    @Autowired
    WxSearchService wxSearchService;

    @RequestMapping("search/index")
    public BaseRespVo search() {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        Map map = wxSearchService.queryHistory();
//        baseRespVo.setData(map);
        return BaseRespVo.success(map);
    }
}
