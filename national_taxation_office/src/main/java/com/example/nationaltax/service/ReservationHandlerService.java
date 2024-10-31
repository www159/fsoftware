package com.example.nationaltax.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.nationaltax.bean.ReservationHandler;
import com.example.nationaltax.mapper.ReservationHandlerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationHandlerService extends ServiceImpl<ReservationHandlerMapper, ReservationHandler> {
    @Autowired
    ReservationHandlerMapper reservationHandlerMapper;
}
