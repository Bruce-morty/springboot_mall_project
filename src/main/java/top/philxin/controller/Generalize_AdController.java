package top.philxin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.Ad;
import top.philxin.model.requestModel.PageHelperVo;
import top.philxin.model.responseModel.BaseRespVo;
import top.philxin.service.Generalize_AdService;

import java.util.HashMap;
import java.util.List;

@RestController
public class Generalize_AdController {

  @Autowired
  Generalize_AdService generalize_adService;
    @RequestMapping("admin/ad/list")
    public BaseRespVo getAd( PageHelperVo pageHelperVo)
    {
        List<Ad> ad = generalize_adService.getAd(pageHelperVo);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("items",ad);
        map.put("total",ad.size());
        return BaseRespVo.success(map);

    }
}
