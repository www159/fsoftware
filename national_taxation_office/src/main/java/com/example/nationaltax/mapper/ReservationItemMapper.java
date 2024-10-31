package com.example.nationaltax.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nationaltax.bean.Privilege;
import com.example.nationaltax.bean.ReservationItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface ReservationItemMapper extends BaseMapper<ReservationItem> {
    @Select("<script>" +
            "SELECT * FROM reservation_item " +
            "<where>" +
            "<if test='name != null'> AND name = #{name} </if>" +
            "<if test='department != null'> AND department = #{department} </if>" +
            "</where>" +
            "</script>")
    List<ReservationItem> searchReservationItemList(@Param("name") String name,
                                               @Param("department") String department);


    @Select("SELECT reservation_item_id FROM nt_oa.reservation_Item WHERE name = #{name};")
    List<Long> getIdsByName(@Param("name") String name);

}
