package com.example.nationaltax.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.nationaltax.bean.NotificationTask;
import com.example.nationaltax.mapper.NotificationTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationTaskService extends ServiceImpl<NotificationTaskMapper, NotificationTask> {
    @Autowired
    NotificationTaskMapper notificationTaskMapper;
}
