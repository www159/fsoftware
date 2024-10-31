package com.example.nationaltax.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nationaltax.bean.Consultation;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ConsultationMapper extends BaseMapper<Consultation> {

}
