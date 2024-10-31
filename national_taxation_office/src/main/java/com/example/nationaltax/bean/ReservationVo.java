package com.example.nationaltax.bean;

import java.util.Date;

import lombok.Data;

@Data
public class ReservationVo {
    private long reservationId;
    private long submitterId;
    private int state;
    private Date reservationTime;
    private String memo;
    private String reserveName;
    private String reserveCompany;
    private String reservePhoneNumber;
    private long reservationItemId;
    private long creatorId;
    private String name;
    private String department;
}
