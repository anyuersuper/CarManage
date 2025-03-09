package com.carmanage.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carmanage.Mapper.cmsubMapper;
import com.carmanage.Mapper.cmusrMapper;
import com.carmanage.Mapper.cmworkorderMapper;
import com.carmanage.dao.cmsub;
import com.carmanage.dao.cmusr;
import com.carmanage.dao.cmworkorder;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/cmworkorder")
public class workorderop {
	private final cmsubMapper cmsubMapper;
	private final cmusrMapper cmusrMapper;
	private final cmworkorderMapper cmworkorderMapper;

    @Autowired
    public workorderop(cmsubMapper cmsubMapper,cmusrMapper cmusrMapper,cmworkorderMapper cmworkorderMapper) {
        this.cmsubMapper = cmsubMapper;
        this.cmusrMapper = cmusrMapper;
        this.cmworkorderMapper = cmworkorderMapper;
    }
	
	@GetMapping("/myworkorder")
    public List<cmworkorder> myworkorder(@CookieValue(value = "username", required = false) String username) {
    	if (username.equals("admin")) {
			return cmworkorderMapper.selectAll();
		}
    	
    	if (cmusrMapper.selectByPrimaryKey(username).getAuthority() == 3) {
    		cmusr user = cmusrMapper.selectByPrimaryKey(username);
    		Integer uid = user.getUid();

    		List<cmworkorder> uidworkorder = cmworkorderMapper.selectByUid(uid);
    		List<cmworkorder> cmuidworkorder = cmworkorderMapper.selectByCmUid(uid);

    		List<cmworkorder> combinedWorkOrders = new ArrayList<>();
    		combinedWorkOrders.addAll(uidworkorder);
    		combinedWorkOrders.addAll(cmuidworkorder);
    		return combinedWorkOrders;
    	}
    	return cmworkorderMapper.selectByUid(cmusrMapper.selectByPrimaryKey(username).getUid());
    }
    
    
    //删除一个记录
    @GetMapping("/delete/{workorderid}")
    public int deleteByPrimaryKey(@PathVariable  Integer workorderid)
    {
    	return cmworkorderMapper.deleteByPrimaryKey(workorderid);
    }
	
    
    
	

}
