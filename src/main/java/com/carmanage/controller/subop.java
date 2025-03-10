package com.carmanage.controller;

import java.rmi.server.UID;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carmanage.Mapper.cmsubMapper;
import com.carmanage.Mapper.cmusrMapper;
import com.carmanage.Mapper.cmworkorderMapper;
import com.carmanage.dao.cmcar;
import com.carmanage.dao.cmsub;
import com.carmanage.dao.cmusr;
import com.carmanage.dao.cmworkorder;

@RestController
@RequestMapping("/cmsub")
public class subop {
	private final cmsubMapper cmsubMapper;
	private final cmusrMapper cmusrMapper;
	private final cmworkorderMapper cmworkorderMapper;

    @Autowired
    public subop(cmsubMapper cmsubMapper,cmusrMapper cmusrMapper,cmworkorderMapper cmworkorderMapper) {
        this.cmsubMapper = cmsubMapper;
        this.cmusrMapper = cmusrMapper;
        this.cmworkorderMapper = cmworkorderMapper;
    }
    
    @GetMapping("/mysub")
    public List<cmsub> mycar(@CookieValue(value = "username", required = false) String username) {
        if (username == null) {
            return new ArrayList<>(); // 避免空指针异常
        }

        cmusr usr = cmusrMapper.selectByPrimaryKey(username);
        if (usr == null) {
            return new ArrayList<>();
        }

        int uid = usr.getUid();
        if (uid == 1 || usr.getAuthority() == 2) {
            return cmsubMapper.selectAll();
        } else { 
            return cmsubMapper.selectByUid(uid);
        }
    }
    
    
    //修改一个记录
    @PostMapping("/update")
    public int updateByPrimaryKey(@RequestBody  cmsub row) 
    {
    	cmsub demo = cmsubMapper.selectByPrimaryKey(row.getSubid());
    	if (demo.getStatus().equals("通过")) {
    		return cmsubMapper.updateByPrimaryKeySelective(row);
		}else {
    		if (row.getStatus().equals("通过")) {
    			cmworkorder cmworkorderrow = new cmworkorder();
    			List<cmworkorder> all = cmworkorderMapper.selectAll();
    			int maxworkorderid = all.stream().mapToInt(cmworkorder::getWorkorderid).max().orElse(-1);
    			cmworkorderrow.setWorkorderid(maxworkorderid + 1);
    			cmworkorderrow.setUid(demo.getUid());
    			cmworkorderrow.setCmuid(3);
    			long currentTimeMillis = System.currentTimeMillis();
    	        Date currentDate = new Date(currentTimeMillis);
    			cmworkorderrow.setStarttime(currentDate);
    			cmworkorderrow.setStatus("处理中");
    			if (cmworkorderMapper.insert(cmworkorderrow) == 1) {
    				return cmsubMapper.updateByPrimaryKeySelective(row);
				}
    			else {
					return 0;
				}
    		}
    		else {
    			return cmsubMapper.updateByPrimaryKeySelective(row);
			}
		}	
    }
    
   //增加一个记录
    @PostMapping("/add")
    public int insert(@RequestBody  cmsub row,@CookieValue(value = "username", required = false) String username) 
    {
    	List<cmsub> all = cmsubMapper.selectAll();
    	int uid   = cmusrMapper.selectByPrimaryKey(username).getUid();
    	row.setUid(uid);
    	int maxsubid = all.stream().mapToInt(cmsub::getSubid).max().orElse(-1);
    	row.setSubid(maxsubid+1);
    	row.setStatus("审批中");
    	return cmsubMapper.insert(row);
    }
    
    
}
