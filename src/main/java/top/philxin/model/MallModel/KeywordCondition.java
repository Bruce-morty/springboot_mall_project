package top.philxin.model.MallModel;

import lombok.Data;

@Data
public class KeywordCondition {
    String keyword;

    String url;

    Integer page;

    Integer limit;

    String sort;

    String order;
}
