package top.philxin.model.GoodsModel;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: CatAndBrandInfoVo
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/27 0027 23:13
 * @version: v1.0
 */
@Data
public class CatAndBrandInfoVo {
    private Integer value;
    private String label;
    private List<CatAndBrandChildInfoVo> children;
}
