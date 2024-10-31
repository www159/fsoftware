package com.example.nationaltax.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nationaltax.bean.ComplaintReply;

@Mapper
public interface ComplaintReplyMapper extends BaseMapper<ComplaintReply> {
    // @Select("select * from complaint_reply where complaint_id = #{id}")
    // public ComplaintReply selectOneByComplaintId(long id);
}
