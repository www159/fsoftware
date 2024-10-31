package com.example.nationaltax.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.nationaltax.bean.*;
import com.example.nationaltax.mapper.PrivilegeMapper;
import com.example.nationaltax.mapper.RoleMapper;
import com.example.nationaltax.mapper.UserMapper;
import com.example.nationaltax.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import java.math.BigDecimal;

import java.util.List;

@Service
@Transactional
public class UserService extends ServiceImpl<UserMapper, User> {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    PrivilegeMapper privilegeMapper;
    @Autowired
    UserRoleMapper userRoleMapper;

    public User getUserByName(String name) {
        return userMapper.selectOneByName(name);
    }

    public User login(String account, String password) {
        return userMapper.selectByAccountAndPassword(account, password);
    }
    //导入用户excel
    public void importExcel(MultipartFile userExcel) {
        try {
            boolean is03Excel = userExcel.getOriginalFilename().matches("^.+\\.(?i)(xls)$");
            //1、读取工作簿
            Workbook workbook = is03Excel ? new HSSFWorkbook(userExcel.getInputStream()) : new XSSFWorkbook(userExcel.getInputStream());
            //2、读取工作表
            Sheet sheet = workbook.getSheetAt(0);
            //3、读取行
            if (sheet.getPhysicalNumberOfRows() > 2) {
                User user = null;
                for (int k = 2; k < sheet.getPhysicalNumberOfRows(); k++) {
                    //4、读取单元格
                    Row row = sheet.getRow(k);
                    user = new User();
                    //用户名
                    Cell cell0 = row.getCell(0);
                    user.setName(cell0.getStringCellValue());
                    //帐号
                    Cell cell1 = row.getCell(1);
                    user.setAccount(cell1.getStringCellValue());
                    //所属部门
                    Cell cell2 = row.getCell(2);
                    user.setDept(cell2.getStringCellValue());
                    //性别
                    Cell cell3 = row.getCell(3);
                    user.setGender(cell3.getStringCellValue().equals("男"));
                    //手机号
                    String mobile = "";
                    Cell cell4 = row.getCell(4);
                    try {
                        mobile = cell4.getStringCellValue();
                    } catch (Exception e) {
                        double dMobile = cell4.getNumericCellValue();
                        mobile = BigDecimal.valueOf(dMobile).toString();
                    }
                    user.setMobile(mobile);

                    //电子邮箱
                    Cell cell5 = row.getCell(5);
                    user.setEmail(cell5.getStringCellValue());
                    //生日
                    Cell cell6 = row.getCell(6);
                    if (cell6.getDateCellValue() != null) {
                        user.setBirthday(cell6.getDateCellValue());
                    }
                    //默认用户密码为 123456
                    user.setPassword("123456");
                    //默认用户状态为 有效
                    user.setState(1);//1为有效

                    //5、保存用户
                    save(user);
                }
            }
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<User> getUsersByRoleName(String roleName) {
        return userMapper.getUsersByRoleName(roleName);
    }

    @Transactional
    public void addUserWithRole(User user, Long roleId) {
        userMapper.insertUser(user);
        userMapper.insertUserRole(user.getId(), roleId);
    }

    public void setUserRole(Long userId, Long roleId) {
        userMapper.insertUserRole(userId, roleId);
    }

    public Long getRoleIdByUserId(Long userId) {
        return userMapper.getRoleIdByUserId(userId);
    }

    public void batchDeleteUsers(List<Long> userIds) {
        // 删除 user_role 表中 user_id 包含在 userIds 列表中的数据
        userRoleMapper.delete(new QueryWrapper<UserRole>().in("user_id", userIds));

        // 删除 user 表中 id 包含在 userIds 列表中的数据
        userMapper.delete(new QueryWrapper<User>().in("id", userIds));
    }





}
