package com.carmanage.Mapper;

import java.util.List;

import com.carmanage.dao.cmpic;

public interface cmpicMapper {
    int deleteByPrimaryKey(Integer picid);

    int insert(cmpic row);

    int insertSelective(cmpic row);

    cmpic selectByPrimaryKey(Integer picid);

    int updateByPrimaryKeySelective(cmpic row);

    int updateByPrimaryKey(cmpic row);
    
    
    List<cmpic> selectBySubid(Integer subid);
}