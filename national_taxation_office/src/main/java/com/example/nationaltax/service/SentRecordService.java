package com.example.nationaltax.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.nationaltax.bean.SentRecord;
import com.example.nationaltax.mapper.SentRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SentRecordService extends ServiceImpl<SentRecordMapper, SentRecord> {
    @Autowired
    SentRecordMapper sentRecordMapper;

    public List<SentRecord> searchNotificationEntries(String phoneNumber, Boolean isSent, String sendMethod) {
        QueryWrapper<SentRecord> queryWrapper = new QueryWrapper<>();

        // 添加手机号码条件
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            queryWrapper.eq("phone_number", phoneNumber);
        }

        // 添加是否已发送条件
        if (isSent != null) {
            queryWrapper.eq("sent", isSent ? 1 : 0);
        }

        // 添加发送方式条件
        if (sendMethod != null && !sendMethod.isEmpty()) {
            queryWrapper.eq("delivery_method", sendMethod);
        }

        return sentRecordMapper.selectList(queryWrapper);
    }


}
