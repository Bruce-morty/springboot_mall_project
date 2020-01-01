package top.philxin.model.WxGoodsModel;

import lombok.Data;
import top.philxin.model.GoodsSpecification;

import java.util.List;

/**
 * @ClassName: WxSpecificationValueVo
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/31 0031 14:11
 * @version: v1.0
 */
@Data
public class WxSpecificationValueVo {
    private String name;
    private List<GoodsSpecification> valueList;
}
