package com.example.nationaltax.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.nationaltax.bean.NotifyMail;
import com.example.nationaltax.mapper.NotifyMailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotifyMailService extends ServiceImpl<NotifyMailMapper, NotifyMail> {
    @Autowired
    NotifyMailMapper notifyMailMapper;
    public void batchInsert(Long notifyId, List<String> mails) {
        for (String mail : mails) {
            notifyMailMapper.insertNotifyMail(notifyId,mail);
        }
    }

    public List<String> getMailByNotifyId(Long notifyId){
        return notifyMailMapper.selectMailByNotifyId(notifyId);
    }

    public List<NotifyMail> getByNotifyId(Long notifyId){
        return notifyMailMapper.selectByNotifyId(notifyId);
    }
}
