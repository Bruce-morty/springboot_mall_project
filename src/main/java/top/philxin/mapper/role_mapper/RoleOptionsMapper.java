package top.philxin.mapper.role_mapper;

import top.philxin.model.AdminModel.RoleOptionsVo;

import java.util.List;

/**
 * @author xqs
 * @version 1.0
 * @description
 * @date 2019/12/27 17:13
 */
public interface RoleOptionsMapper {
    List<RoleOptionsVo> selectAllRole();
}
