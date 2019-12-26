package top.philxin.model.MallModel;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class Brand {
    //加上@Data注解，取出get和set方法
    private Integer id;

    private String name;

    private String desc;

    private String picUrl;

    private Byte sortOrder;

    private BigDecimal floorPrice;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;

}