package com.carmanage.controller;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carmanage.Mapper.cmcarMapper;
import com.carmanage.Mapper.cmusrMapper;
import com.carmanage.dao.cmcar;
import com.carmanage.dao.cmusr;

@RestController
@RequestMapping("/cmcar")
public class carop {
	private final cmcarMapper cmcarMapper;
	private final cmusrMapper cmusrMapper;
    @Autowired
    public carop(cmcarMapper cmcarMapper,cmusrMapper cmusrMapper) {
        this.cmcarMapper = cmcarMapper;
        this.cmusrMapper = cmusrMapper;
    }
    
    @GetMapping("/search/{carid}")
    public cmcar selectByPrimaryKey(@PathVariable  Integer carid)
    {
    	return cmcarMapper.selectByPrimaryKey(carid);
    }
    
    //增加一个记录
    @PostMapping("/add")
    public int insert(@RequestBody  cmcar row) 
    {
    	return cmcarMapper.insert(row);
    }
    
    //删除一个记录
    @GetMapping("/delete/{carid}")
    public int deleteByPrimaryKey(@PathVariable  Integer carid)
    {
    	return cmcarMapper.deleteByPrimaryKey(carid);
    }
    
    //修改一个记录
    @PostMapping("/update")
    public int updateByPrimaryKey(@RequestBody  cmcar row) 
    {
    	return cmcarMapper.updateByPrimaryKey(row);
    }
    
	
    //获取车辆信息
    @GetMapping("/mycar")
    public List<cmcar> mycar(@CookieValue(value = "username", required = false) String username) {
        if (username == null) {
            return new ArrayList<>(); // 避免空指针异常
        }

        cmusr usr = cmusrMapper.selectByPrimaryKey(username);
        if (usr == null) {
            return new ArrayList<>();
        }

        int uid = usr.getUid();
        if (uid == 1) { // 管理员返回所有车辆数据
            return cmcarMapper.selectAll();
        } else { // 普通用户返回自己拥有的车辆
            return cmcarMapper.selectByUid(uid);
        }
    }

}
