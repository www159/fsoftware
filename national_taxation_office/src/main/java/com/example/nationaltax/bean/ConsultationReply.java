package com.example.nationaltax.bean;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("consultation_reply")
public class ConsultationReply {
    @TableId(type=IdType.AUTO)
    private long consultationReplyId;
    private long consultationId;
    private long replyerId;
    private String content;
    private Date time;
}
