package com.example.nationaltax.bean;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@TableName("info")
public class Info {
    @TableId(type = IdType.AUTO)
    private long infoId;
    private int type;
    private String source;
    private String title;
    private String content;
    private String memo;
    private String creatorName;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date time;
    private int state;

    // 公告状态
    public static int INFO_STATE_VALID = 1;
    public static int INFO_STATE_INVALID = 0;

    // 公告类型 @todo
    public static int INFO_TYPE_NULL = 0;
}
