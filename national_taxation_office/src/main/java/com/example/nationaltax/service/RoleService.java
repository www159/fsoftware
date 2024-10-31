package com.example.nationaltax.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.nationaltax.bean.Privilege;
import com.example.nationaltax.bean.Role;
import com.example.nationaltax.bean.RolePrivilege;
import com.example.nationaltax.bean.User;
import com.example.nationaltax.mapper.PrivilegeMapper;
import com.example.nationaltax.mapper.RoleMapper;
import com.example.nationaltax.mapper.RolePrivilegeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    PrivilegeMapper privilegeMapper;
    @Autowired
    RolePrivilegeMapper rolePrivilegeMapper;
    public Role findRoleById(long roleId) {
        return roleMapper.findRoleById(roleId);
    }


    public List<Privilege> getRolePrivileges(Long roleId) {
        List<RolePrivilege> rolePrivileges = roleMapper.findRolePrivileges(roleId); 
        List<Privilege> privileges = new ArrayList<>();
        for (RolePrivilege rolePrivilege : rolePrivileges) {
            Privilege privilege = privilegeMapper.findPrivilegeById(rolePrivilege.getPrivilegeId());
            if (privilege != null) {
                privileges.add(privilege);
            }
        }
        return privileges;
    }

    public List<String> getPrivilegeNamesByRoleId(Long roleId) {
        List<String> privilegeNames = roleMapper.getPrivilegeNamesByRoleId(roleId);
        return privilegeNames;
    }

    @Transactional
    public void addPrivilege(Long roleId, Long privilegeId) {

        roleMapper.insertRolePrivilege(roleId,privilegeId);
    }

    public void batchDeleteRoles(List<Long> roleIds) {
        // 删除 role_privilege 表中 role_id 包含在 roleIds 列表中的数据
        roleMapper.delete(new QueryWrapper<Role>().in("role_id", roleIds));

        // 删除 role 表中 role_id 包含在 roleIds 列表中的数据
        roleMapper.delete(new QueryWrapper<Role>().in("role_id", roleIds));
    }

}
