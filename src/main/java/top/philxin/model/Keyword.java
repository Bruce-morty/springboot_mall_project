package top.philxin.model;

import lombok.Data;

import java.util.Date;

@Data
public class Keyword {
    private Integer id;

    private String keyword;

    private String url;

    private Boolean isHot;

    private Boolean isDefault;

    private Integer sortOrder;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;
}