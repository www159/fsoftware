package com.example.nationaltax.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@TableName("reservation_item")
@Data
public class ReservationItem {
    @TableId(type = IdType.AUTO)
    private long reservationItemId;
    private long creatorId;
    private String name;
    private String department;

}
