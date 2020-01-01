package top.philxin.model.requestModel;

import lombok.Data;

/**
 * @ClassName: WxUserRegisterVo
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/31 0031 22:45
 * @version: v1.0
 */
@Data
public class WxUserRegisterVo {
    private String username;
    private String password;
    private String mobile;
    private String code;
    private String wxCode;
}
