package com.carmanage.Mapper;

import java.util.List;

import com.carmanage.dao.cmcar;

public interface cmcarMapper {
    int deleteByPrimaryKey(Integer carid);

    int insert(cmcar row);

    int insertSelective(cmcar row);

    cmcar selectByPrimaryKey(Integer carid);

    int updateByPrimaryKeySelective(cmcar row);

    int updateByPrimaryKey(cmcar row);
    
    List<cmcar> selectAll();
    
    List<cmcar> selectByUid(int uid);
}