package top.philxin.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private Integer id;

    private Integer valueId;

    private Byte type;

    private String content;

    private Integer userId;

    private Boolean hasPicture;

    private String[] picUrls;

    private Short star;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Date addTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Date updateTime;

    private Boolean deleted;

    private Integer orderGoodsId;
}
