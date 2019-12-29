package top.philxin.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Category {
    private Integer id;

    private String name;

    private String keywords;

    private String desc;

    private Integer pid;

    private String iconUrl;

    private String picUrl;

    private String level;

    private Byte sortOrder;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;

    private List<Category> children;
}