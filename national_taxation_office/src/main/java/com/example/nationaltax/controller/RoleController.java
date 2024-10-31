package com.example.nationaltax.controller;

import com.example.nationaltax.bean.Privilege;
import com.example.nationaltax.bean.Role;
import com.example.nationaltax.bean.RolePrivilege;
import com.example.nationaltax.bean.User;
import com.example.nationaltax.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PutMapping("/update/{id}")
    public String updateRole(@PathVariable Long id, @RequestBody Role role) {
        role.setRoleId(id);
        roleService.updateById(role);
        return "Role updated successfully";
    }

    @PostMapping("/addRole")
    public String addRole(@RequestBody Role role) {
        roleService.save(role);
        return "Role added successfully";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRole(@PathVariable Long id) {
        roleService.removeById(id);
        return "Role deleted successfully";
    }

    @GetMapping("/getRoleById/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable long id) {
        Role role = roleService.findRoleById(id);
        if (role != null) {
            return ResponseEntity.ok(role);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAll")
    public List<Role> getAllRoles() {
        return roleService.list();
    }

    @GetMapping("/getPrivileges/{roleId}")
    public ResponseEntity<List<String>> getPrivilegeNamesByRoleId(@PathVariable Long roleId) {
        List<String> privilegeNames = roleService.getPrivilegeNamesByRoleId(roleId);
        return ResponseEntity.ok(privilegeNames);
    }

    @PostMapping
    public ResponseEntity<String> addPrivilege(@RequestParam Long roleId,@RequestParam Long privilegeId) {
        roleService.addPrivilege(roleId,privilegeId);
        String message = "权限添加成功，角色设置成功";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @DeleteMapping("/batch")
    public String batchDeleteRoles(@RequestBody List<Long> roleIds) {
        // 批量删除角色及关联数据的逻辑
        roleService.batchDeleteRoles(roleIds);

        return "Batch delete roles and associated privileges completed.";
    }

}
