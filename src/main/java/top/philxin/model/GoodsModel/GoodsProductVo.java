package top.philxin.model.GoodsModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: GoodsProductVo
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/28 0028 22:36
 * @version: v1.0
 */
@Data
public class GoodsProductVo {
    private Integer id;

    private Integer goodsId;

    private String specifications;

    private BigDecimal price;

    private Integer number;

    private String url;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date addTime;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;

    private Boolean deleted;
}
