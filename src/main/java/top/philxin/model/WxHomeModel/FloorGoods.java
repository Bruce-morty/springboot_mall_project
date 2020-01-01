package top.philxin.model.WxHomeModel;

import lombok.Data;
import top.philxin.model.Goods;

import java.util.List;

@Data
public class FloorGoods {
    String name;

    List<Goods> goodsList;
}
