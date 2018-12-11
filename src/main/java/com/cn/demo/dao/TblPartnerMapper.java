package com.cn.demo.dao;

import com.cn.demo.model.Partner;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

@Mapper
public interface TblPartnerMapper extends BaseMapper<Partner> {

    /**
     * 1：查询权限
     *
     * @param valid
     * @return
     */
    List<Partner> queryByValid(String valid);
}
