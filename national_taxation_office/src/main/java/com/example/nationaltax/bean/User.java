package com.example.nationaltax.bean;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("user")
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private long id;
    private String dept;
    private String company;
    private String account;
    private String name;
    private String password;

    private String headImg;
    private boolean gender;
    private int state;
    private String mobile;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date birthday;
    private String memo;
    private int admin;

    //用户状态
    public static String USER_STATE_VALID = "1";//有效
    public static String USER_STATE_INVALID = "0";//无效
}
