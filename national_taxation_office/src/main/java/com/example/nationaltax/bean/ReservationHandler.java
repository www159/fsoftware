package com.example.nationaltax.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@TableName("reservation_handler")
@Data
public class ReservationHandler {
    @TableId(type = IdType.AUTO)
    private long reservationHandlerId;
    private long handlerId;
    private String content;
    private int reservationResult;
    private long reservationId;

}
