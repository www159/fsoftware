package com.example.nationaltax.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nationaltax.bean.ConsultationReply;

@Mapper
public interface ConsultationReplyMapper extends BaseMapper<ConsultationReply> {
    @Select("select * from consultation_reply where consultation_id = #{id}")
    ConsultationReply selectOneByConsultationId(long id);
}
