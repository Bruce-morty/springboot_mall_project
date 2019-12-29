package top.philxin.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author  xqs
 * @description
 * @date  2019/12/28 22:24
 * @version 1.0
 */
@Data
public class AllAuth implements Serializable {
    private Integer primaryId;

    private String id;

    private String label;

    private Integer pid;

    private String api;

    private List<AllAuth> children;

    private static final long serialVersionUID = 1L;
}
