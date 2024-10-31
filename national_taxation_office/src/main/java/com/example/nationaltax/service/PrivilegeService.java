package com.example.nationaltax.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.nationaltax.bean.Privilege;
import com.example.nationaltax.mapper.PrivilegeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PrivilegeService extends ServiceImpl<PrivilegeMapper,Privilege> {
    @Autowired
    PrivilegeMapper privilegeMapper;

    public List<Privilege> getUserPrivilegesByUserId(Long id) {
        return privilegeMapper.getUserPrivilegesByUserId(id);
    }

}
