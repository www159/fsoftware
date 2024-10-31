package com.example.nationaltax.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nationaltax.bean.Privilege;
import com.example.nationaltax.bean.User;
import com.example.nationaltax.bean.UserRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from nt_oa.user where name = #{name}")
    User selectOneByName(String name);

    @Select("SELECT * FROM nt_oa.user WHERE account = #{account} AND password = #{password}")
    User selectByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    @Select("SELECT DISTINCT u.* FROM nt_oa.user u " +
            "INNER JOIN nt_oa.user_role ur ON u.id = ur.user_id " +
            "INNER JOIN nt_oa.role r ON ur.role_id = r.role_id " +
            "WHERE r.role_name = #{roleName}")
    List<User> getUsersByRoleName(@Param("roleName") String roleName);

    @Insert("INSERT INTO user (dept, company, account, name, password, head_img, gender, state, mobile, email, birthday, memo) " +
            "VALUES (#{user.dept}, #{user.company}, #{user.account}, #{user.name}, #{user.password}, #{user.headImg}, #{user.gender}, " +
            "#{user.state}, #{user.mobile}, #{user.email}, #{user.birthday}, #{user.memo})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(@Param("user") User user);

    @Insert("INSERT INTO user_role (user_id, role_id) VALUES (#{userId}, #{roleId})")
    void insertUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

    @Select("SELECT role_id\n" +
            "FROM nt_oa.user_role\n" +
            "WHERE user_id = #{userId};\n")
    Long getRoleIdByUserId(Long userId);

}
