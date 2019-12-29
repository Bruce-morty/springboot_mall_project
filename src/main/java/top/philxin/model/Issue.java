package top.philxin.model;

import lombok.Data;

import java.util.Date;

@Data
public class Issue {
    private Integer id;

    private String question;

    private String answer;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;
}