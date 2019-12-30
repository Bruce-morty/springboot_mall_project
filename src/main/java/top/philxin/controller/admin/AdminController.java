package top.philxin.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.annotation.LogRecordAnno;
import top.philxin.model.Admin;
import top.philxin.model.AdminModel.ChangePermission;
import top.philxin.model.AdminModel.RoleOptionsVo;
import top.philxin.model.Role;
import top.philxin.model.Storage;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.model.responseModel.CommonsModel.BaseDataVo;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.admin.AdminService;

import java.util.List;
import java.util.Map;

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
    @LogRecordAnno(operateAction = "新增管理员")
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
        Integer id = admin.getId();
        String msg = "新增管理员编号：" + id;
        return BaseRespVo.success(admin,msg);
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
    @LogRecordAnno(operateAction = "删除管理员")
    public BaseRespVo removeAdmin(@RequestBody Admin admin){
        adminService.removeAdmin(admin);
        Integer id = admin.getId();
        String msg = "删除管理员编号：" + id;
        return BaseRespVo.success(msg);
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
    @LogRecordAnno(operateAction = "增加角色")
    public BaseRespVo addRole(@RequestBody Role role){
        adminService.addOneRole(role);
        Integer id = role.getId();
        String msg = "新增角色编号：" + id;
        return BaseRespVo.success(role,msg);
    }

    /**
     * 删除选中的role
     * @param role
     * @return
     */
    @RequestMapping("role/delete")
    @LogRecordAnno(operateAction = "删除角色")
    public BaseRespVo removeRole(@RequestBody Role role){
        adminService.removeRole(role);
        Integer id = role.getId();
        String msg = "删除角色编号：" + id;
        return BaseRespVo.success(msg);
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
     * 获得选中对象的权限
     * @param roleId
     * @return
     */
    @RequestMapping(value = "role/permissions",method = RequestMethod.GET)
    public BaseRespVo getAllAuth(Integer roleId){
        Map map = adminService.querySelectetAuth(roleId);
        return BaseRespVo.success(map);
    }


    @RequestMapping(value = "role/permissions",method = RequestMethod.POST)
    public BaseRespVo changeAuth(@RequestBody ChangePermission changePermission){
        adminService.changeAuth(changePermission);
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
    @LogRecordAnno(operateAction = "删除对象")
    public BaseRespVo removeStorage(@RequestBody Storage storage){
        adminService.removeStorage(storage);
        Integer id = storage.getId();
        String msg = "删除对象编号：" + id;
        return BaseRespVo.success(msg);
    }

}
