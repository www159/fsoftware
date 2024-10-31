package com.example.nationaltax.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nationaltax.bean.Complaint;

@Mapper
public interface ComplaintMapper extends BaseMapper<Complaint> {
    
}
