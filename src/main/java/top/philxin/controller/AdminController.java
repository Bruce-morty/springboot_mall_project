package top.philxin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.Admin;
import top.philxin.model.AdminModel.RoleOptionsVo;
import top.philxin.model.Role;
import top.philxin.model.Storage;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.model.responseModel.CommonsModel.BaseDataVo;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.AdminService;

import java.util.List;

/**
 * @author xqs
 * @version 1.0
 * @description
 * @date 2019/12/27 16:09
 */
@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    /**
     * 获得整个admin表
     * @param pageHelperVo
     * @param username
     * @return
     */
    @RequestMapping("admin/list")
    public BaseRespVo getAllUser(PageHelperVo pageHelperVo,String username){
        BaseDataVo baseDataVo = adminService.queryUsers(pageHelperVo, username);
        return BaseRespVo.success(baseDataVo);
    }

    /**
     * 获得整个role表
     * @return
     */
    @RequestMapping("role/options")
    public BaseRespVo getAllRole(){
        List<RoleOptionsVo> roleList = adminService.queryRoles();
        return BaseRespVo.success(roleList);
    }

    /**
     * 添加新的admin
     * @param admin
     * @return
     */
    @RequestMapping("admin/create")
    public BaseRespVo addAdmin(@RequestBody Admin admin){
        String username = admin.getUsername();
        String password = admin.getPassword();
        boolean flag = adminService.querySameName(username);
        if (!flag){
            return BaseRespVo.error(602,"管理员已经存在");
        }
        if (password.length()<6 ){
            return BaseRespVo.error(602,"密码长度不能小于6");
        }
        if (username.length()<6){
            return BaseRespVo.error(601,"管理员名称不符合规定");
        }
        adminService.addOneAdmin(admin);
        return BaseRespVo.success(admin);
    }

    /**
     * 更改admin信息
     * @param admin
     * @return
     */
    @RequestMapping("admin/update")
    public BaseRespVo changeAdmin(@RequestBody Admin admin){
        adminService.changeAdminMsg(admin);
        return BaseRespVo.success(admin);
    }

    /**
     * 删除选中admin
     * @param admin
     * @return
     */
    @RequestMapping("admin/delete")
    public BaseRespVo removeAdmin(@RequestBody Admin admin){
        adminService.removeAdmin(admin);
        return BaseRespVo.success();
    }

    /**
     * 获得所有Log
     * @param pageHelperVo
     * @param name
     * @return
     */
    @RequestMapping("log/list")
    public BaseRespVo getAllLog(PageHelperVo pageHelperVo,String name){
        BaseDataVo baseDataVo = adminService.queryLogs(pageHelperVo, name);
        return BaseRespVo.success(baseDataVo);
    }

    /**
     * 获得所有role
     * @param pageHelperVo
     * @param name
     * @return
     */
    @RequestMapping("role/list")
    public BaseRespVo getAllRole(PageHelperVo pageHelperVo,String name){
        BaseDataVo baseDataVo = adminService.queryRoles(pageHelperVo, name);
        return BaseRespVo.success(baseDataVo);
    }

    /**
     * 增加一个role
     * @param role
     * @return
     */
    @RequestMapping("role/create")
    public BaseRespVo addRole(@RequestBody Role role){
        adminService.addOneRole(role);
        return BaseRespVo.success(role);
    }

    /**
     * 删除选中的role
     * @param role
     * @return
     */
    @RequestMapping("role/delete")
    public BaseRespVo removeRole(@RequestBody Role role){
        adminService.removeRole(role);
        return BaseRespVo.success();
    }

    /**
     * 更改选中的role的信息
     * @param role
     * @return
     */
    @RequestMapping("role/update")
    public BaseRespVo changeRole(@RequestBody Role role){
        adminService.changeRoleMsg(role);
        return BaseRespVo.success();
    }

    /**
     * 获得整个storage
     * @param pageHelperVo
     * @param key
     * @param name
     * @return
     */
    @RequestMapping("storage/list")
    public BaseRespVo getAllStorage(PageHelperVo pageHelperVo,String key,String name){
        BaseDataVo baseDataVo = adminService.queryStorages(pageHelperVo, key, name);
        return BaseRespVo.success(baseDataVo);

    }

    /**
     * 更改选中的storage
     * @param storage
     * @return
     */
    @RequestMapping("storage/update")
    public BaseRespVo changeStorage(@RequestBody Storage storage){
        adminService.changeStorageMsg(storage);
        return BaseRespVo.success(storage);
    }

    /**
     * 删除选中的storage
     * @param storage
     * @return
     */
    @RequestMapping("storage/delete")
    public BaseRespVo removeStorage(@RequestBody Storage storage){
        adminService.removeStorage(storage);
        return BaseRespVo.success();
    }

}
