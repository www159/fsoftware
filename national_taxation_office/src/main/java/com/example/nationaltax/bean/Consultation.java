package com.example.nationaltax.bean;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("consultation")
public class Consultation {
    @TableId(type = IdType.AUTO)
    private long consultationId;
    private long consultId;
    private Date time;
    private String title;
    private int type;
    private int state;
    private String name;
    private String mobile;
    private String company;
    private String content;

    public static int CONSULTATION_STATE_UNHANDLED = 0;
    public static int COLSULTATION_STATE_HANDLED = 1;
    public static int CONSULTATION_STATE_NEVER = 2;
}
