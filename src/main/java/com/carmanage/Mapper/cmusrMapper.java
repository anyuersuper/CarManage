package com.carmanage.Mapper;

import java.util.List;

import com.carmanage.dao.cmusr;

public interface cmusrMapper {
    int deleteByPrimaryKey(String username);

    int insert(cmusr row);

    int insertSelective(cmusr row);

    cmusr selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(cmusr row);

    int updateByPrimaryKey(cmusr row);
    
    List<cmusr> selectAll();
}