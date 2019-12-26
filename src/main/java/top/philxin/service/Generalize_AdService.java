package top.philxin.service;

import top.philxin.model.Ad;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import java.util.List;

public interface Generalize_AdService {
     List<Ad> getAd(PageHelperVo pageHelperVo,String name,String content);
}
