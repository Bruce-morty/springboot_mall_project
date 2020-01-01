package top.philxin.model;

import lombok.Data;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {
    private Integer id;

    private String name;

    private Integer userId;

    private Integer provinceId;

    private String provinceName;

    private Integer cityId;

    private String cityName;

    private Integer areaId;

    private String areaName;

    private StringBuffer detailedAddress;

    private String address;

    private String mobile;

    private Boolean isDefault;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Boolean deleted;
}
