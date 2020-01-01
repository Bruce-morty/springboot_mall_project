package top.philxin.controller.wx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.WxGoodsModel.WxGoodsDetailVo;
import top.philxin.model.WxGoodsModel.WxGoodsListVo;
import top.philxin.model.requestModel.CommonsModel.WxPageHelperVo;
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

    /**
     * 显示商品详细信息
     * @param id
     * @return
     */
    @RequestMapping("detail")
    public BaseRespVo getGoodsDetail(Integer id) {
        WxGoodsDetailVo wxGoodsDetailVo = wxGoodsService.getGoodsDetailInfo(id);
        return BaseRespVo.success(wxGoodsDetailVo);
    }

    /**
     * 获取商品列表
     * @param wxPageHelperVo 分页插件
     * @param brandId 品牌id
     * @param categoryId 分类id
     * @param keyword 关键字查询
     * @return
     */
    @RequestMapping("list")
    public BaseRespVo getGoodsList(WxPageHelperVo wxPageHelperVo, Integer brandId, Integer categoryId, String keyword) {
        WxGoodsListVo wxGoodsListVo = wxGoodsService.getGoodsList(wxPageHelperVo, brandId, categoryId, keyword);
        return BaseRespVo.success(wxGoodsListVo);
    }
}
