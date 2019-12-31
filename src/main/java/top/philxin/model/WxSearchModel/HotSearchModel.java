package top.philxin.model.WxSearchModel;

import lombok.Data;

/**
 * @ClassName:
 * @Description: zy
 * @author:
 * @date:
 * @version: v1.0
 */

@Data
public class HotSearchModel {

    String addTime;
    boolean deleted;
    int id;
    boolean isDefault;
    boolean isHot;
    String keyword;
    int sortOrder;
    String updateTime;
    String url;

}
