package top.philxin.model.responseModel.WxUserModel;

import lombok.Data;

/**
 * @ClassName: WxUserIndexOrderStatusInfoVo
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/30 0030 21:52
 * @version: v1.0
 */
@Data
public class WxUserIndexOrderStatusInfoVo {
    private int unpaid;
    private int unrecv;
    private int unship;
    private int uncommnet;
}
