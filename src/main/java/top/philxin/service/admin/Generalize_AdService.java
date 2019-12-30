package top.philxin.service.admin;

import top.philxin.model.Ad;
import top.philxin.model.Coupon;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import java.util.List;
import java.util.Map;

public interface Generalize_AdService {
     Map getAd(PageHelperVo pageHelperVo, String name, String content);

    Ad addAd(Ad ad);


    Ad updateAd(Ad ad);

    int deleteAd(Ad ad);
}
