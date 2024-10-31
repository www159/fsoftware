package com.example.nationaltax.bean;   

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@TableName("complaint")
public class Complaint {
    @TableId(type = IdType.AUTO)
    private long complaintId;
    private String complaintCompany;
    private String complaintName;
    private long userId;
    private String mobile;
    private boolean anonymous;
    // @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date time;
    private String title;
    private String toComplaintName;
    private String toComplaintDept;
    private String content;
    private int state;

    static final int COMPLAINT_STATE_UNHANDLE = 0;
    static final int COMPLAINT_STATE_HANDLED = 1;
}