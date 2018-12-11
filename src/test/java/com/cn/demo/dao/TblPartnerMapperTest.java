package com.cn.demo.dao;

import com.cn.demo.model.Partner;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TblPartnerMapperTest implements TblPartnerMapper {
    @Autowired
    private TblPartnerMapper tblPartnerMapper;

    @Test
    public void queryByValid() {
        Page<Object> objects = PageHelper.startPage(1, 7).doSelectPage(() -> tblPartnerMapper.queryByValid("1"));
/*
           PageHelper.startPage(1, 7);
        List<Partner> partners= tblPartnerMapper.queryByValid("1");
*/



        System.out.println("第一次："+objects);


    }



    @Override
    public int deleteByPrimaryKey(Object o) {
        return 0;
    }

    @Override
    public int delete(Partner partner) {
        return 0;
    }

    @Override
    public int insert(Partner partner) {
        return 0;
    }

    @Override
    public int insertSelective(Partner partner) {
        return 0;
    }

    @Override
    public boolean existsWithPrimaryKey(Object o) {
        return false;
    }

    @Override
    public List<Partner> selectAll() {
        return null;
    }

    @Override
    public Partner selectByPrimaryKey(Object o) {
        return null;
    }

    @Override
    public int selectCount(Partner partner) {
        return 0;
    }

    @Override
    public List<Partner> select(Partner partner) {
        return null;
    }

    @Override
    public Partner selectOne(Partner partner) {
        return null;
    }

    @Override
    public int updateByPrimaryKey(Partner partner) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Partner partner) {
        return 0;
    }

    @Override
    public List<Partner> queryByValid(String valid) {
        return null;
    }
}