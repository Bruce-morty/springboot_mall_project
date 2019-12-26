package top.philxin.service;

import top.philxin.model.Ad;
import top.philxin.model.requestModel.PageHelperVo;

import java.util.List;

public interface Generalize_AdService {
     List<Ad> getAd(PageHelperVo pageHelperVo);
}
