package com.example.nationaltax.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.nationaltax.bean.EasyNotify;
import com.example.nationaltax.bean.NotifyMail;
import com.example.nationaltax.mapper.EasyNotifyMapper;
import com.example.nationaltax.mapper.EasyNotifyPhoneNumbersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EasyNotifyService extends ServiceImpl<EasyNotifyMapper, EasyNotify> {
    @Autowired
    EasyNotifyMapper easyNotifyMapper;
    @Autowired
    SentRecordService notificationEntryService;
    @Autowired
    EasyNotifyPhoneNumbersMapper easyNotifyPhoneNumbersMapper;

    public void addEasyNotify(EasyNotify easyNotifyRecord) {

        easyNotifyMapper.insert(easyNotifyRecord);
    }

    public boolean updateStatusById(Long id, String status) {
        EasyNotify easyNotify = new EasyNotify();
        easyNotify.setId(id);
        easyNotify.setStatus(status);
        int rows = easyNotifyMapper.updateById(easyNotify);
        return rows > 0;
    }

    public List<EasyNotify> getAllEasyNotifys() {

        return easyNotifyMapper.selectList(null);
    }

    public void saveEasyNotify(EasyNotify easyNotify) {
        // 保存EasyNotify对象到主表
        easyNotifyMapper.insert(easyNotify);
    }

    public List<EasyNotify> searchEasyNotifyList(String title, String type, String status) {
        return baseMapper.searchEasyNotifyList(title, type, status);
    }

    public void savePhoneNumber(Long notifyId, String phoneNumber) {
        easyNotifyMapper.insertPhoneNumber(notifyId, phoneNumber);
    }


}