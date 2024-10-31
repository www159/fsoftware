package com.example.nationaltax.bean;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role_privilege")
public class RolePrivilege implements Serializable {
    @Id
    private Long id;
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "privilege_id")
    private Long privilegeId;
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Long privilegeId) {
        this.privilegeId = privilegeId;
    }


}
