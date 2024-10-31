package com.example.nationaltax.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("easy_notify_phone_numbers")
@Data
public class EasyNotifyPhoneNumbers {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long notifyId; // Foreign key referencing 'easy_notify' table's primary key
    private String phoneNumber;
}
