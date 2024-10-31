package com.example.nationaltax.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nationaltax.bean.Privilege;
import com.example.nationaltax.bean.Role;
import com.example.nationaltax.bean.RolePrivilege;
import com.example.nationaltax.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    @Select("SELECT * FROM nt_oa.role WHERE role_id = #{roleId};")
    Role findRoleById(@Param("roleId") long roleId);

    @Select("SELECT * FROM nt_oa.role_privilege WHERE role_id = #{roleId};")
    List<RolePrivilege> findRolePrivileges(@Param("roleId") Long roleId);

   @Select("SELECT p.name\n" +
           "FROM nt_oa.role_privilege rp\n" +
           "JOIN nt_oa.privilege p ON rp.privilege_id = p.privilege_id\n" +
           "WHERE rp.role_id = #{roleId};")
   List<String> getPrivilegeNamesByRoleId(Long roleId);

    @Insert("INSERT INTO role_privilege (role_id, privilege_id) VALUES (#{roleId}, #{privilegeId})")
    void insertRolePrivilege(@Param("roleId") Long roleId, @Param("privilegeId") Long privilegeId);

}
