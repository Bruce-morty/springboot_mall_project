package top.philxin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.MallModel.Region;
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
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        baseRespVo.setData(regionList);
        return baseRespVo;
    }

    /**
     * 获取全部的品牌制造商及相关信息
     * @return
     */
    @RequestMapping("brand/list")
    public BaseRespVo getBrandList() {
        return null;
    }
}
