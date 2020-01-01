package top.philxin.model.WxSearchModel;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName:
 * @Description: zy
 * @author:
 * @date:
 * @version: v1.0
 */

@Data
public class SearchHistoryModel {

    int id;
    int userId;
    String keyword;
    String from;
    Date addTime;
    Date updateTime;
    boolean deleted;

}
