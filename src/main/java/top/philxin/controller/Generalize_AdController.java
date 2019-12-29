package top.philxin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.Ad;
import top.philxin.model.Coupon;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.Generalize_AdService;
import top.philxin.service.Generalize_couponService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Generalize_AdController {

  @Autowired
  Generalize_AdService adService;

    /**
     *
     * 按条件查询广告消息
     * @param pageHelperVo
     * @param name
     * @param content
     * @return
     */
    @RequestMapping("admin/ad/list")
    public BaseRespVo getAd(PageHelperVo pageHelperVo,String name,String content)
    {
        Map adMap = adService.getAd(pageHelperVo, name, content);

        return BaseRespVo.success(adMap);

    }
    /**
     * 添加广告
     */
     @RequestMapping("admin/ad/create")
    public BaseRespVo addAd(@RequestBody Ad ad)
    {
        Ad ad1 = adService.addAd(ad);
        return BaseRespVo.success(ad1);
    }
    /**
     * 更新广告
     */
    @RequestMapping("admin/ad/update")
     public BaseRespVo updateAd(@RequestBody Ad ad)
    {
        Ad ad1 = adService.updateAd(ad);
        return BaseRespVo.success(ad1);
    }
    /**
     * 删除广告
     */
    @RequestMapping("admin/ad/delete")
    public BaseRespVo deleteAd(@RequestBody Ad ad)
    {
      adService.deleteAd(ad);
      return BaseRespVo.success();

    }


}
