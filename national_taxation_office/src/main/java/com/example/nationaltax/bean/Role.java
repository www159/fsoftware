package com.example.nationaltax.bean;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@TableName("role")
public class Role implements Serializable {
    @TableId(type = IdType.AUTO)
    private long roleId;
    private String roleName;
    private int state;

    //角色状态
    public static String ROLE_STATE_VALID = "1";//有效
    public static String ROLE_STATE_INVALID = "0";//无效

    public Role(long roleId) {
        this.roleId = roleId;
    }
    public long getRoleId() {
        return roleId;
    }
    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }


}