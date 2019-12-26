package top.philxin.model.requestModel.CommonsModel;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @ClassName: PageHelperVo
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/26 0026 15:23
 * @version: v1.0
 */
@Data
public class PageHelperVo {
    private Integer page;
    private Integer limit;
    private String sort;
    private String order;
}
