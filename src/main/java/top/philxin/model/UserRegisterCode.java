package top.philxin.model;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName: UserRegisterCode
 * @Description: TODO
 * @author: Administrator
 * @date: 2020/1/1 0001 16:06
 * @version: v1.0
 */
@Data
public class UserRegisterCode {

    private Integer id;

    private String mobile;

    private String code;

    private Date expiredTime;

    private Date nonrepeatTime;

}
