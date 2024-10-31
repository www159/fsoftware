package com.example.nationaltax.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.nationaltax.bean.Complaint;
import com.example.nationaltax.mapper.ComplaintMapper;

@Service
public class ComplaintService extends ServiceImpl<ComplaintMapper, Complaint> {
    @Autowired
    private ComplaintMapper complaintMapper;
}
