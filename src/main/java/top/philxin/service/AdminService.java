package top.philxin.service;

import top.philxin.model.Admin;
import top.philxin.model.AdminModel.RoleOptionsVo;
import top.philxin.model.Role;
import top.philxin.model.Storage;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.model.responseModel.CommonsModel.BaseDataVo;

import java.util.List;

/**
 * @author xqs
 * @version 1.0
 * @description
 * @date 2019/12/27 16:12
 */
public interface AdminService {
    //获得admin整个列表
    BaseDataVo queryUsers(PageHelperVo pageHelperVo,String username);
    List<RoleOptionsVo> queryRoles();

    //添加一个新的admin
    void addOneAdmin(Admin admin);
    //查询表里是否已经存在此用户名
    boolean querySameName(String username);

    //更改已存在的admin的信息
    void changeAdminMsg(Admin admin);

    //删除选定的admin
    void removeAdmin(Admin admin);

    //获得整个log表
    BaseDataVo queryLogs(PageHelperVo pageHelperVo,String name);

    //获得整个role表
    BaseDataVo queryRoles(PageHelperVo pageHelperVo,String name);

    //添加一个role
    void addOneRole(Role role);

    //删除选中的role
    void removeRole(Role role);

    //更改选中role的信息
    void changeRoleMsg(Role role);

    //获得整个storage表
    BaseDataVo queryStorages(PageHelperVo pageHelperVo,String key,String name);

    //更改选中storage信息
    void changeStorageMsg(Storage storage);

    //删除选中storage信息
    void removeStorage(Storage storage);
}
