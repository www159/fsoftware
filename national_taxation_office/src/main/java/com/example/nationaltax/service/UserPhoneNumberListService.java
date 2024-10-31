package com.example.nationaltax.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.nationaltax.bean.UserPhoneNumberList;
import com.example.nationaltax.mapper.UserPhoneNumberListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPhoneNumberListService extends ServiceImpl<UserPhoneNumberListMapper,UserPhoneNumberList> {
    @Autowired
    UserPhoneNumberListMapper userPhoneNumberListMapper;

    public void addUserPhoneNumberList(UserPhoneNumberList userPhoneNumberList) {
        userPhoneNumberListMapper.insert(userPhoneNumberList);
    }

    public List<UserPhoneNumberList> getAllUserPhoneNumberLists() {
        return userPhoneNumberListMapper.selectList(null);
    }
}