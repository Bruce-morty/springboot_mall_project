package top.philxin.controller.wx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.wx.WxCatalogService;

import java.util.Map;

/**
 * @author xqs
 * @version 1.0
 * @description
 * @date 2019/12/30 17:56
 */
@RestController
@RequestMapping("wx")
public class WxCatalogController {
    @Autowired
    WxCatalogService wxCatalogService;

    /**
     * 获得全部标签及初始标签信息
     * @return
     */
    @RequestMapping("catalog/index")
    public BaseRespVo queryAllIndex(){
    Map map = wxCatalogService.queryAllIndex();
    return BaseRespVo.success(map);
    }

    /**
     * 获得选中的标签信息
     * @param id
     * @return
     */
    @RequestMapping("catalog/current")
    public BaseRespVo queryCurrentIndex(Integer id){
        Map map = wxCatalogService.queryCurrentIndex(id);
        return BaseRespVo.success(map);
    }
}
