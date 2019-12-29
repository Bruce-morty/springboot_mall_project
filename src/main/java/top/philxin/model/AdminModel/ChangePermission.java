package top.philxin.model.AdminModel;

import lombok.Data;
import top.philxin.model.Permission;

import java.util.List;

/**
 * @author xqs
 * @version 1.0
 * @description
 * @date 2019/12/29 17:08
 */
@Data
public class ChangePermission {
    Integer roleId;
    List<String> permissions;
}
