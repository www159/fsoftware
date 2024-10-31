package com.example.nationaltax.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nationaltax.bean.Privilege;
import com.example.nationaltax.bean.Role;
import com.example.nationaltax.bean.RolePrivilege;
import com.example.nationaltax.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PrivilegeMapper extends BaseMapper<Privilege>{
    @Select("SELECT * FROM nt_oa.role_privilege WHERE role_id = #{roleId};")
    List<RolePrivilege> findRolePrivileges(@Param("roleId") Long roleId);
    @Select("SELECT * FROM nt_oa.privilege WHERE privilege_id = #{privilegeId};")
    Privilege findPrivilegeById(@Param("privilegeId") Long privilegeId);

    @Select("SELECT p.* FROM nt_oa.user u JOIN nt_oa.user_role ur ON u.id = ur.user_id JOIN nt_oa.role r ON ur.role_id = r.role_id JOIN nt_oa.role_privilege rp ON r.role_id = rp.role_id JOIN nt_oa.privilege p ON rp.privilege_id = p.privilege_id WHERE u.id = #{id}")
    List<Privilege> getUserPrivilegesByUserId(Long id);


}
