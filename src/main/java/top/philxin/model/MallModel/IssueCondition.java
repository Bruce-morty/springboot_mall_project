package top.philxin.model.MallModel;

import lombok.Data;

@Data
public class IssueCondition {
    String question;

    Integer page;

    Integer limit;

    String sort;

    String order;
}
