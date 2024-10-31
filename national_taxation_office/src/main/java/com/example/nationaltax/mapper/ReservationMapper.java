package com.example.nationaltax.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.nationaltax.bean.Reservation;
import com.example.nationaltax.bean.ReservationVo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReservationMapper extends BaseMapper<Reservation> {
    @Select("SELECT r.* FROM reservation r LEFT JOIN reservation_item ri ON r.reservation_item_id = ri.reservation_item_id WHERE ri.name LIKE CONCAT('%', #{name}, '%')")
    List<Reservation> selectByItemName(@Param("name") String name);

    @Select("SELECT * FROM reservation WHERE state = #{state}")
    List<Reservation> selectByState(@Param("state") Integer state);

    @Select("SELECT * FROM reservation WHERE reservation_item_id = #{reservationItemId}")
    List<Reservation> findByReservationItemId(@Param("reservationItemId") long reservationItemId);

    @Select("SELECT *, ri.name as reservation_item_name     FROM reservation r JOIN reservation_item ri ON r.reservation_item_id = ri.reservation_item_id ${ew.customSqlSegment}")
    Page<ReservationVo> selectReservationList(Page<ReservationVo> page, @Param(Constants.WRAPPER) Wrapper<ReservationVo> queryWrapper);
}
