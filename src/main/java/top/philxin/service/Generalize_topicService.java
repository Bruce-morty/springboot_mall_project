package top.philxin.service;

import top.philxin.model.Coupon;
import top.philxin.model.CouponUser;
import top.philxin.model.Topic;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;

import java.util.List;
import java.util.Map;

public interface Generalize_topicService {
    Map queryTopic(PageHelperVo pageHelperVo, String title, String subtitle);

    int deleteTopic(Topic topic);

    Topic addTopic(Topic topic);

    Topic updateTopic(Topic topic);
}
