package com.example.nationaltax.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.nationaltax.bean.LoginDto;
import com.example.nationaltax.bean.User;
import com.example.nationaltax.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUserByName/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name) {
        User user = userService.getUserByName(name);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginDto loginDto) {
        User user = userService.login(loginDto.getAccount(), loginDto.getPassword());

        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(user);
        }
    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        userService.updateById(user);
        return "User updated successfully";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        userService.save(user);
        return "User added successfully";
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userService.list();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.removeById(id);
        return "User deleted successfully";
    }


    @PostMapping("/importExcel")
    public void importExcel(MultipartFile userExcel) {
        userService.importExcel(userExcel);
    }

    @GetMapping("/by-role/{roleName}")
    public List<User> getUsersByRoleName(@PathVariable String roleName) {
        return userService.getUsersByRoleName(roleName);
    }

    @PostMapping
    public ResponseEntity<String> addUserWithRole(@RequestBody User user,@RequestParam Long roleId) {
        userService.addUserWithRole(user,roleId);
        String message = "用户添加成功，角色设置成功";
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }

    @PostMapping("/setUserRole")
    public ResponseEntity<String> setUserRole(@RequestParam Long userId, @RequestParam Long roleId) {
        try {
            userService.setUserRole(userId, roleId);
            String message = "用户角色设置成功";
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            String errorMessage = "设置用户角色失败：" + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping("/getRoleId/{userId}")
    public Long getRoleIdByUserId(@PathVariable("userId") Long userId) {

        return userService.getRoleIdByUserId(userId);
    }

    // @DeleteMapping("/batch")
    // public String batchDeleteUsers(@RequestBody List<Long> userIds) {
    //     // 批量删除角色及关联数据的逻辑
    //     userService.batchDeleteUsers(userIds);

    //     return "Batch delete users and associated roles completed.";
    // }



}
