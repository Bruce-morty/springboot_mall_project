package top.philxin.model.GoodsModel;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: CatAndBrandVo
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/27 0027 21:50
 * @version: v1.0
 */
@Data
public class CatAndBrandVo {
    private List<CatAndBrandInfoVo> categoryList;
    private List<CatAndBrandChildInfoVo> brandList;
}
