package com.example.nationaltax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.nationaltax.bean.Consultation;
import com.example.nationaltax.bean.ConsultationVO;
import com.example.nationaltax.mapper.ConsultationMapper;

@Service
public class ConsultationService extends ServiceImpl<ConsultationMapper, Consultation> {
    @Autowired
    ConsultationMapper consultationMapper;
}
