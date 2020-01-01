package top.philxin.model.WxGoodsModel;

import lombok.Data;
import top.philxin.model.Category;
import top.philxin.model.Goods;

import java.util.List;

/**
 * @ClassName: WxGoodsListVo
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/31 0031 17:09
 * @version: v1.0
 */
@Data
public class WxGoodsListVo {

    private List<Goods> goodsList;

    private Integer count;

    private List<Category> filterCategoryList;
}
