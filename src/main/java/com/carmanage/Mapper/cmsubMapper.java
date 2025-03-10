package com.carmanage.Mapper;

import java.util.List;

import com.carmanage.dao.cmsub;

public interface cmsubMapper {
    int deleteByPrimaryKey(Integer subid);

    int insert(cmsub row);

    int insertSelective(cmsub row);

    cmsub selectByPrimaryKey(Integer subid);

    int updateByPrimaryKeySelective(cmsub row);

    int updateByPrimaryKey(cmsub row);
    
    List<cmsub> selectAll();
    
    List<cmsub> selectByUid(int uid);
}