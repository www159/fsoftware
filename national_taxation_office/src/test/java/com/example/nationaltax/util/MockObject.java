package com.example.nationaltax.util;

import java.util.Date;

import com.example.nationaltax.bean.Complaint;
import com.example.nationaltax.bean.Consultation;
import com.example.nationaltax.bean.Info;

public class MockObject {
    public static Info mockInfo(String keyword) {
        Info info = new Info();
        info.setSource(keyword + " source");
        info.setTitle(keyword + " title");
        info.setContent(keyword + " content");
        info.setMemo(keyword + " memo");
        info.setCreatorName(keyword + " name");
        info.setTime(new Date());
        return info;
    }

    public static Complaint mockComplaint(String keyword) {
        Complaint complaint = new Complaint();

        complaint.setComplaintCompany(keyword + " complaint company");
        complaint.setComplaintName(keyword + " complaint name");
        complaint.setMobile(keyword + " mobile");
        complaint.setAnonymous(false);
        complaint.setTime(new Date());
        complaint.setTitle(keyword + " title");
        complaint.setToComplaintName(keyword + " name");
        complaint.setToComplaintDept(keyword + " department");
        complaint.setContent(keyword + " content");
        complaint.setState(0);

        return complaint;
    }

    public static Consultation mockConsultation(String keyword) {
        Consultation consultation = new Consultation();

        consultation.setConsultId(0);
        consultation.setTime(new Date());
        consultation.setTitle(keyword + " title");
        consultation.setType(0);
        consultation.setState(0);
        consultation.setName(keyword + " name");
        consultation.setMobile(keyword + " mobile");
        consultation.setCompany(keyword + " company");

        return consultation;
    }
}
