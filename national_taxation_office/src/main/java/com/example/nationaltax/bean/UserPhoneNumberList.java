package com.example.nationaltax.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_phone_number_list")
@Data
public class UserPhoneNumberList {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String excelFile;
    private Date createdTime;
}
