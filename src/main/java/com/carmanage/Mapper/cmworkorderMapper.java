package com.carmanage.Mapper;

import java.util.List;

import com.carmanage.dao.cmworkorder;

public interface cmworkorderMapper {
    int deleteByPrimaryKey(Integer workorderid);

    int insert(cmworkorder row);

    int insertSelective(cmworkorder row);

    cmworkorder selectByPrimaryKey(Integer workorderid);

    int updateByPrimaryKeySelective(cmworkorder row);

    int updateByPrimaryKey(cmworkorder row);
    
    List<cmworkorder> selectAll();
    List<cmworkorder> selectByUid(Integer uid);
    List<cmworkorder> selectByCmUid(Integer cmuid);
}