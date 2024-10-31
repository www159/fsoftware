package com.example.nationaltax.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.nationaltax.bean.EasyNotify;
import com.example.nationaltax.bean.ReservationItem;
import com.example.nationaltax.mapper.ReservationItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationItemService extends ServiceImpl<ReservationItemMapper, ReservationItem> {
    @Autowired
    ReservationItemMapper reservationItemMapper;
    public List<ReservationItem> searchReservationItemList(String name, String department) {
        return baseMapper.searchReservationItemList (name , department);
    }

    public List<Long> getReservationItemIdsByName(String name) {
        return reservationItemMapper.getIdsByName(name);
    }
}
