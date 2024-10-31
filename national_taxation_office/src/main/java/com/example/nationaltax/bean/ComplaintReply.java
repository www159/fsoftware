package com.example.nationaltax.bean;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("complaint_reply")
public class ComplaintReply {
    @TableId(type = IdType.AUTO)
    private long complaintReplyId;
    private long complaintId;
    private long replyerId;
    private String content;
    private Date time;
}
