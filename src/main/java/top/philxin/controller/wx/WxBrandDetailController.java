package top.philxin.controller.wx;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.Brand;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.wx.WxBrandService;

import java.util.HashMap;

/**
 * @ClassName: WxBrandDetailController
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/31 0031 16:04
 * @version: v1.0
 */
@RestController
@RequestMapping("wx/brand")
public class WxBrandDetailController {

    @Autowired
    WxBrandService wxBrandService;

    @RequestMapping("detail")
    public BaseRespVo getBrandDetail(Integer id) {
        Brand brand = wxBrandService.getBrandById(id);
        HashMap<String, Object> map = new HashMap();
        map.put("brand",brand);
        return BaseRespVo.success(map);
    }
}
