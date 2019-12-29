package top.philxin.model.GeneralizeModel;

import lombok.Data;
import top.philxin.model.GrouponRules;

import java.util.List;
@Data
public class GrouponActivities {
    private Integer orderId;
    private Integer userId;
    private List<GrouponRules> grouponRulesList;
}
