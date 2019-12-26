package top.philxin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.MallModel.Brand;
import top.philxin.model.MallModel.BrandCondition;
import top.philxin.model.MallModel.Region;
import top.philxin.model.responseModel.CommonsModel.BaseDataVo;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.MallService;

import java.util.List;

@RestController
@RequestMapping("admin")
public class MallController {
    @Autowired
    MallService mallService;
    /**
     * 用于获取全部的行政区域
     * @return
     */
    @RequestMapping("region/list")
    public BaseRespVo getAllRegion() {
        List<Region> regionList = mallService.getAllRegion();
        BaseRespVo baseRespVo = BaseRespVo.success(regionList);
        return baseRespVo;
    }

    /**
     * 根据名称和id获取制造商信息
     * @return
     */
    @RequestMapping("brand/list")
    public BaseRespVo getBrandListByPage(BrandCondition brandCondition) {
        BaseDataVo<Brand> baseDataVo = mallService.getBrandListByPage(brandCondition);
        BaseRespVo baseRespVo = BaseRespVo.success(baseDataVo);
        return baseRespVo;
    }
}
