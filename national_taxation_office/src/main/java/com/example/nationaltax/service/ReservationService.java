package com.example.nationaltax.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.nationaltax.bean.Reservation;
import com.example.nationaltax.bean.ReservationItem;
import com.example.nationaltax.bean.SentRecord;
import com.example.nationaltax.mapper.ReservationItemMapper;
import com.example.nationaltax.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService extends ServiceImpl<ReservationMapper, Reservation> {
    @Autowired
    ReservationMapper reservationMapper;
    @Autowired
    ReservationItemMapper reservationItemMapper;

    public List<Long> getReservationItemIdsByName(String name) {
        return reservationItemMapper.getIdsByName(name);
    }

    public List<Reservation> getReservationsByState(Integer state) {
        return reservationMapper.selectByState(state);
    }

    public List<Reservation> getReservationByItemName(String itemName) {
        return reservationMapper.selectByItemName(itemName);
    }

    public List<Reservation> findByReservationItemId(long reservationItemId) {
        return reservationMapper.findByReservationItemId(reservationItemId);
    }


}
