package com.example.nationaltax.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.nationaltax.bean.Info;
import com.example.nationaltax.mapper.InfoMapper;

@Service
public class InfoService extends ServiceImpl<InfoMapper, Info> {
    @Autowired
    InfoMapper infoMapper;
}
