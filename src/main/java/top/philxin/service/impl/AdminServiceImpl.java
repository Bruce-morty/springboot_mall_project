package top.philxin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.AdminMapper;
import top.philxin.mapper.LogMapper;
import top.philxin.mapper.RoleMapper;
import top.philxin.mapper.StorageMapper;
import top.philxin.mapper.role_mapper.RoleOptionsMapper;
import top.philxin.model.*;
import top.philxin.model.AdminModel.RoleOptionsVo;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.model.responseModel.CommonsModel.BaseDataVo;
import top.philxin.service.AdminService;

import java.lang.System;
import java.util.Date;
import java.util.List;

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
    StorageMapper storageMapper;
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
        //这里注意它自动生成的sql语句没有没关键字加''
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
        roleMapper.insert(role);
    }

    /**
     * 删除一个选中role
     * @param role
     */
    @Override
    public void removeRole(Role role) {
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
        if (key!=null && key.length()!=0){
            criteria.andKeyLike("%"+key+"%");
        }
        if (name!=null && name.length()!=0){
            criteria.andNameLike("%"+name+"%");
        }
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
        storageMapper.deleteByPrimaryKey(storage.getId());
    }
}
