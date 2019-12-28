package top.philxin.model;

import lombok.Data;

import java.util.List;

@Data
public class Region {
    //增加@Data注解，删除多余的get和set方法
    //增加属性List<Region>以便封装数据
    private Integer id;

    private Integer pid;

    private String name;

    private Byte type;

    private Integer code;

    private List<Region> children;
}