package top.philxin.controller.wx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.WxGoodsModel.WxGoodsDetailVo;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.wx.WxGoodsService;

/**
 * @ClassName: WxGoodsDetailController
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/31 0031 14:09
 * @version: v1.0
 */
@RestController
@RequestMapping("wx/goods")
public class WxGoodsDetailController {

    @Autowired
    WxGoodsService wxGoodsService;

    @RequestMapping("detail")
    public BaseRespVo getGoodsDetail(Integer id) {
        WxGoodsDetailVo wxGoodsDetailVo = wxGoodsService.getGoodsDetailInfo(id);
        return BaseRespVo.success(wxGoodsDetailVo);
    }
}
