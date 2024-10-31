package com.example.nationaltax.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nationaltax.bean.NotifyMail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NotifyMailMapper extends BaseMapper<NotifyMail> {
    @Insert("INSERT INTO notify_mail (notify_id, mail) VALUES (#{notifyId}, #{mail})")
    void insertNotifyMail(@Param("notifyId") Long notifyId, @Param("mail") String mail);

    @Select("SELECT mail FROM nt_oa.notify_mail WHERE notify_id = #{notifyId}")
    List<String> selectMailByNotifyId(@Param("notifyId")Long notifyId);

    @Select("SELECT * FROM nt_oa.notify_mail WHERE notify_id = #{notifyId}")
    List<NotifyMail> selectByNotifyId(@Param("notifyId")Long notifyId);
}
