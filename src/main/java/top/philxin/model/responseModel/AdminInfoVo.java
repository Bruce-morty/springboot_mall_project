package top.philxin.model.responseModel;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: AdminInfoVo
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/26 0026 10:24
 * @version: v1.0
 */
@Data
public class AdminInfoVo {
    private String name;
    private String avatar;
    private List<String> roles;
    private List<String> perms;
}
