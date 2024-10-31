package com.example.nationaltax.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@TableName("reservation")
@Data
public class Reservation {
    @TableId(type = IdType.AUTO)
    private long reservationId;
    private long reservationItemId;
    private long submitterId;
    private int state;
    // @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date reservationTime;
    private String memo;
    private String reserveName;
    private String reserveCompany;
    private String reservePhoneNumber;

}
