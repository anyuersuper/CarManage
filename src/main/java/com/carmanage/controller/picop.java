package com.carmanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carmanage.Mapper.cmpicMapper;
import com.carmanage.Mapper.cmsubMapper;
import com.carmanage.dao.cmpic;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/cmpic")
public class picop {
	
	private final cmpicMapper cmpicMapper;
	private final cmsubMapper cmsubMapper;

    @Autowired
    public picop(cmpicMapper cmpicMapper,cmsubMapper cmsubMapper) {
        this.cmpicMapper = cmpicMapper;
        this.cmsubMapper = cmsubMapper;
    }
	
    //查找记录根据subid
    @GetMapping("/find/{subid}")
    public List<cmpic> selectByPrimaryKey(@PathVariable  Integer subid)
    {
    	return cmpicMapper.selectBySubid(subid);
    }
    
    
    //subdetail
    @GetMapping("/detailpic/{subid}")
    public String getMethodName(@RequestParam Integer  subid) {
    	cmsubMapper.selectByPrimaryKey(subid);
    	
        return new String();
    }
    
    
    
	

}
