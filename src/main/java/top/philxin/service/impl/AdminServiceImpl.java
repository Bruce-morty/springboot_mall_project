package top.philxin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.*;
import top.philxin.mapper.role_mapper.RoleOptionsMapper;
import top.philxin.model.*;
import top.philxin.model.AdminModel.ChangePermission;
import top.philxin.model.AdminModel.RoleOptionsVo;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.model.responseModel.CommonsModel.BaseDataVo;
import top.philxin.service.AdminService;

import java.lang.System;
import java.util.*;

/**
 * @author xqs
 * @version 1.0
 * @description
 * @date 2019/12/27 16:12
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Autowired
    BaseDataVo baseDataVo;

    @Autowired
    RoleOptionsMapper roleOptionsMapper;

    @Autowired
    LogMapper logMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    AllAuthMapper allAuthMapper;

    @Autowired
    StorageMapper storageMapper;

    List<AllAuth> outAuths;
    @Override
    public BaseDataVo queryUsers(PageHelperVo pageHelperVo, String username) {
        PageHelper.startPage(pageHelperVo.getPage(),pageHelperVo.getLimit());
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        adminExample.setOrderByClause(pageHelperVo.getSort()+" "+pageHelperVo.getOrder());
        if (username!=null && username.length()!=0) {
            criteria.andUsernameLike("%"+username+"%");
        }
        //获得admin的列表
        criteria.andDeletedEqualTo(false);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        PageInfo<Admin> adminPageInfo = new PageInfo<>(admins);
        long total = adminPageInfo.getTotal();
        //把数据塞进baseDataVo中
        baseDataVo.setItems(admins);
        baseDataVo.setTotal((int) total);
        return baseDataVo;
    }

    /**
     * 获得所有role
     * @return
     */
    @Override
    public List<RoleOptionsVo> queryRoles() {
        List<RoleOptionsVo> roleList = roleOptionsMapper.selectAllRole();
        return roleList;
    }

    /**
     * 新增一个admin
     * @param admin
     */
    @Override
    public void addOneAdmin(Admin admin) {
        Date date = new Date();
        admin.setAddTime(date);
        admin.setDeleted(false);
        adminMapper.insert(admin);
    }

    //判断admin表中是否存在此用户
    @Override
    public boolean querySameName(String username) {
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (admins==null || admins.size()==0){
            return true;
        }else {
        return false;
        }
    }

    /**
     * 更改选中admin
     * @param admin
     */
    @Override
    public void changeAdminMsg(Admin admin) {
        Date date = new Date();
        admin.setAddTime(date);
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andIdEqualTo(admin.getId());
        adminMapper.updateByExample(admin,adminExample);
    }

    /**
     * 删除选中admin
     * @param admin
     */
    @Override
    public void removeAdmin(Admin admin) {
        admin.setDeleted(true);
        adminMapper.deleteByPrimaryKey(admin.getId());
    }

    /**
     * 查找log中所有的信息
     * @param pageHelperVo
     * @param name
     * @return
     */
    @Override
    public BaseDataVo queryLogs(PageHelperVo pageHelperVo, String name) {
        PageHelper.startPage(pageHelperVo.getPage(),pageHelperVo.getLimit());
        LogExample logExample = new LogExample();
        LogExample.Criteria criteria = logExample.createCriteria();
        logExample.setOrderByClause(pageHelperVo.getSort()+" "+pageHelperVo.getOrder());
        if (name!=null && name.length()!=0){
            criteria.andAdminLike("%"+name+"%");
        }
        criteria.andDeletedEqualTo(false);
        List<Log> logs = logMapper.selectByExample(logExample);
        PageInfo<Log> pageInfo = new PageInfo<>();
        long total = pageInfo.getTotal();
        baseDataVo.setTotal((int) total);
        baseDataVo.setItems(logs);
        return baseDataVo;
    }

    /**
     * 获取整个role表
     * @param pageHelperVo
     * @param name
     * @return
     */
    @Override
    public BaseDataVo queryRoles(PageHelperVo pageHelperVo, String name) {
        PageHelper.startPage(pageHelperVo.getPage(),pageHelperVo.getLimit());
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        roleExample.setOrderByClause(pageHelperVo.getSort()+" "+pageHelperVo.getOrder());
        if (name!=null && name.length()!=0){
            criteria.andNameLike("%"+name+"%");
        }
        //这里注意它自动生成的sql语句没有没关键字加``
        criteria.andDeletedEqualTo(false);
        List<Role> roles = roleMapper.selectByExample(roleExample);
        PageInfo<Role> pageInfo = new PageInfo<>();
        long total = pageInfo.getTotal();
        baseDataVo.setTotal((int) total);
        baseDataVo.setItems(roles);
        return baseDataVo;
    }

    /**
     * 增加一个role
     * @param role
     */
    @Override
    public void addOneRole(Role role) {
        Date date = new Date();
        role.setAddTime(date);
        role.setDeleted(false);
        roleMapper.insert(role);
    }

    /**
     * 删除一个选中role
     * @param role
     */
    @Override
    public void removeRole(Role role) {
        role.setDeleted(true);
        roleMapper.deleteByPrimaryKey(role.getId());
    }

    /**
     * 更改选中role信息
     * @param role
     */
    @Override
    public void changeRoleMsg(Role role) {
        Date date = new Date();
        role.setAddTime(date);
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        System.out.println(role.getId());
        criteria.andIdEqualTo(role.getId());
        roleMapper.updateByExample(role,roleExample);
    }


    /**
     * 获得当前对象的权限
     * @param roleId
     * @return
     */
    @Override
    public Map querySelectetAuth(Integer roleId) {
        Map map = new HashMap();
        PermissionExample permissionExample = new PermissionExample();
        PermissionExample.Criteria criteria = permissionExample.createCriteria();
        criteria.andRoleIdEqualTo(roleId).andDeletedEqualTo(false);
        List<Permission> permissions = permissionMapper.selectByExample(permissionExample);
        //这里把当前对象已有权限塞入map
        List<String> permList = new ArrayList<>();
        for (Permission permission : permissions) {
            String perm = permission.getPermission();
            permList.add(perm);
        }
        map.put("assignedPermissions",permList);
        //这里可节省第二次读取时间
        if (outAuths!=null){
            map.put("systemPermissions",outAuths);
            return map;
        }
        //建立最外层
        outAuths = new ArrayList<>();
        //建立中间层
        ArrayList<AllAuth> midAuths = new ArrayList<>();
        //建立最里层
        ArrayList<AllAuth> innerAuths = new ArrayList<>();

        //获得最外层
        List<AllAuth> allAuths = allAuthMapper.selectByExample(new AllAuthExample());
        for (AllAuth allAuth : allAuths) {
            if (allAuth.getPid()==0){
                outAuths.add(allAuth);
                continue;
            }
            if (allAuth.getApi()!=null){
                innerAuths.add(allAuth);
                continue;
            }
            midAuths.add(allAuth);
        }
        for (AllAuth midAuth : midAuths) {
            List<AllAuth> tempList = new ArrayList<>();
            for (AllAuth innerAuth : innerAuths) {
                if (innerAuth.getPid()==midAuth.getPrimaryId()){
                    tempList.add(innerAuth);
                }
            }
            midAuth.setChildren(tempList);
        }

        for (AllAuth outAuth : outAuths) {
            List<AllAuth> tempList = new ArrayList<>();
            for (AllAuth midAuth : midAuths) {
                if (outAuth.getPrimaryId()==midAuth.getPid()){
                    tempList.add(midAuth);
                }
            }
            outAuth.setChildren(tempList);
        }
        map.put("systemPermissions",outAuths);
        return map;
    }

    /**
     * 更改当前对象的权限
     */
    @Override
    public void changeAuth(ChangePermission changePermission) {
        Integer roleId = changePermission.getRoleId();
        PermissionExample permissionExample = new PermissionExample();
        PermissionExample.Criteria criteria = permissionExample.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        permissionMapper.deleteByExample(permissionExample);
        List<String> permissions = changePermission.getPermissions();
        for (String perm : permissions) {
            Date date = new Date();
            Permission permission = new Permission();
            permission.setRoleId(roleId);
            permission.setPermission(perm);
            permission.setAddTime(date);
            permission.setUpdateTime(date);
            permission.setDeleted(false);
            permissionMapper.insert(permission);
        }
        //获得原先表中存在的列表
        /*List<Permission> permissionsPast = permissionMapper.selectByExample(permissionExample);
        //把原先有的permission存到一个列表中
        List<String> permList = new ArrayList();
        for (Permission permission : permissionsPast) {
            permList.add(permission.getPermission());
        }

        //这是现在从前端传回来的permission数据
        List<String> permissions = changePermission.getPermissions();
        for (String permStr : permissions) {
            for (String s : permList) {
                if (s.equals(permStr)){
                    Date date = new Date();
                    PermissionExample permissionExample1 = new PermissionExample();
                    PermissionExample.Criteria criteria1 = permissionExample.createCriteria();
                    criteria1.andPermissionEqualTo(s);

                }
            }
        }*/

    }

    /**
     * 获得整个storage表
     * @param pageHelperVo
     * @param key
     * @param name
     * @return
     */
    @Override
    public BaseDataVo queryStorages(PageHelperVo pageHelperVo, String key, String name) {
        PageHelper.startPage(pageHelperVo.getPage(),pageHelperVo.getLimit());
        StorageExample storageExample = new StorageExample();
        StorageExample.Criteria criteria = storageExample.createCriteria();
        storageExample.setOrderByClause(pageHelperVo.getSort()+" "+pageHelperVo.getOrder());
        //这里的key要进入example中加``
        if (key!=null && key.length()!=0){
            criteria.andKeyEqualTo(key);
        }
        if (name!=null && name.length()!=0){
            criteria.andNameLike("%"+name+"%");
        }
        criteria.andDeletedEqualTo(false);
        List<Storage> storages = storageMapper.selectByExample(storageExample);
        PageInfo<Storage> pageInfo = new PageInfo<>();
        long total = pageInfo.getTotal();
        baseDataVo.setTotal((int) total);
        baseDataVo.setItems(storages);
        return baseDataVo;
    }

    /**
     * 更改选中storage
     * @param storage
     */
    @Override
    public void changeStorageMsg(Storage storage) {
        Date date = new Date();
        storage.setAddTime(date);
        StorageExample storageExample = new StorageExample();
        StorageExample.Criteria criteria = storageExample.createCriteria();
        criteria.andIdEqualTo(storage.getId());
        storageMapper.updateByExample(storage,storageExample);
    }

    /**
     * 删除选中storage
     * @param storage
     */
    @Override
    public void removeStorage(Storage storage) {
        storage.setDeleted(true);
        storageMapper.deleteByPrimaryKey(storage.getId());
    }
}
