package com.example.nationaltax.bean;

import java.sql.Date;
import lombok.Data;

@Data
public class ConsultationVO {
    private long consultationId;
    private long consultId;
    private String consulCompany;
    private String consulName;
    private String consulMobile;
    private Date time;
    private String title;
    private int type;
    private int state;
}
