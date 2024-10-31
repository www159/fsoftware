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
@TableName("sent_record")
@Data
public class SentRecord {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String phoneNumber;
    private String email;
    private boolean sent;
    private String result;
    private String deliveryMethod;
    private Date sentTime;
    private Long taskId;
}
