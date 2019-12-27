package top.philxin.model.responseModel.GoodsModel;

import lombok.Data;
import top.philxin.model.GoodsAttribute;
import top.philxin.model.GoodsModel.Goods;
import top.philxin.model.GoodsProduct;
import top.philxin.model.GoodsSpecification;

import java.util.List;

/**
 * @ClassName: GoodsInfoDetail
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/27 0027 16:38
 * @version: v1.0
 */
@Data
public class GoodsInfoDetailVo {
    private List<Integer> categoryIds;
    private Goods goods;
    private List<GoodsAttribute> attributes;
    private List<GoodsSpecification> specifications;
    private List<GoodsProduct> products;
}
