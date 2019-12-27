package top.philxin.model.MallModel;

import lombok.Data;

import java.util.List;

@Data
public class OrderCondition {
    private Integer page;
    private Integer limit;
    private String sort;
    private String order;
    private List<Short> orderStatusArray;
    private String orderSn;
    private Integer userId;
}
