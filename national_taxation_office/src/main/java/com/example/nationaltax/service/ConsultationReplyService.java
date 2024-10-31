package com.example.nationaltax.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.nationaltax.bean.ConsultationReply;
import com.example.nationaltax.mapper.ConsultationReplyMapper;

@Service
public class ConsultationReplyService extends ServiceImpl<ConsultationReplyMapper, ConsultationReply> {
    @Autowired
    ConsultationReplyMapper consultationReplyMapper;

    public ConsultationReply getByConsultationId(long id) {
        return consultationReplyMapper.selectOneByConsultationId(id);
    }
}
