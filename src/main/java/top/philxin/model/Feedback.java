package top.philxin.model;

import lombok.Data;

import java.util.Date;

@Data
public class Feedback {
    private Integer id;

    private Integer userId;

    private String username;

    private String mobile;

    private String feedType;

    private String content;

    private Integer status;

    private Boolean hasPicture;

    private String[] picUrls;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;
}