package top.philxin.model.requestModel.CommonsModel;

import lombok.Data;

/**
 * @ClassName: WxPageHelperVo
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/31 0031 16:34
 * @version: v1.0
 */
@Data
public class WxPageHelperVo {
    private Integer page;
    private Integer size;
    private String sort;
    private String order;
}
