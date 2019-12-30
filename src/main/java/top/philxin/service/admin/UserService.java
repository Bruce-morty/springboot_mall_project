package top.philxin.service.admin;

import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.model.responseModel.CommonsModel.BaseDataVo;


/**
 * @ClassName: UserService
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/26 0026 16:34
 * @version: v1.0
 */
public interface UserService {
    BaseDataVo queryUsers(PageHelperVo pageHelperVo, String username, String mobile);


    BaseDataVo queryAddresss(PageHelperVo pageHelperVo, String userId, String name);

    BaseDataVo queryCollects(PageHelperVo pageHelperVo, String userId, String valueId);

    BaseDataVo queryFeedbacks(PageHelperVo pageHelperVo, String username, String id);

    BaseDataVo queryFootprints(PageHelperVo pageHelperVo, String userId, String goodsId);

    BaseDataVo querySearchHistory(PageHelperVo pageHelperVo, String userId, String keyword);

}
