package com.example.nationaltax.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nationaltax.bean.EasyNotify;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EasyNotifyMapper extends BaseMapper<EasyNotify> {
    @Select("<script>" +
            "SELECT * FROM easy_notify " +
            "<where>" +
            "<if test='title != null'> AND title = #{title} </if>" +
            "<if test='type != null'> AND type = #{type} </if>" +
            "<if test='status != null'> AND status = #{status} </if>" +
            "</where>" +
            "</script>")
    List<EasyNotify> searchEasyNotifyList(@Param("title") String title,
                                          @Param("type") String type,
                                          @Param("status") String status);

    @Insert("INSERT INTO nt_oa.easy_notify_phone_numbers (notify_id, phone_number) VALUES (#{notifyId}, #{phoneNumber})")
    void insertPhoneNumber(@Param("notifyId") Long notifyId, @Param("phoneNumber") String phoneNumber);
}
