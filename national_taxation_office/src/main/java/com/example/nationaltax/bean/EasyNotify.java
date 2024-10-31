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
@TableName("easy_notify")
@Data
public class EasyNotify {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    private String type;
    private String status;
    private String creator;
    private Date createdTime;
}
