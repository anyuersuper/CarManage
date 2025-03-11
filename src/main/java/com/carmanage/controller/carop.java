package com.carmanage.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.carmanage.Mapper.cmcarMapper;
import com.carmanage.Mapper.cmusrMapper;
import com.carmanage.dao.cmcar;
import com.carmanage.dao.cmusr;
import com.carmanage.dao.cmworkorder;
import com.carmanage.service.BaiduImageRecognitionService;
import com.carmanage.service.ImageRecognitionService;

@RestController
@RequestMapping("/cmcar")
public class carop {
	private final cmcarMapper cmcarMapper;
	private final cmusrMapper cmusrMapper;
	private final ImageRecognitionService recognitionService;
	
    @Autowired
    public carop(cmcarMapper cmcarMapper,cmusrMapper cmusrMapper) {
        this.cmcarMapper = cmcarMapper;
        this.cmusrMapper = cmusrMapper;
        this.recognitionService = new BaiduImageRecognitionService();
    }
    
    @GetMapping("/search/{carid}")
    public cmcar selectByPrimaryKey(@PathVariable  Integer carid)
    {
    	return cmcarMapper.selectByPrimaryKey(carid);
    }
    
    //增加一个记录
    @PostMapping("/add")
    public int insert(@RequestBody  cmcar row,@CookieValue(value = "username", required = false) String username) 
    {
    	List<cmcar> all = cmcarMapper.selectAll();
		int maxcarid = all.stream().mapToInt(cmcar::getCarid).max().orElse(0);
		int carid = maxcarid + 1;
		row.setCarid(carid);
    	cmusr user = cmusrMapper.selectByPrimaryKey(username);
    	row.setUid(user.getUid());
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

    
    //自动识别车辆
    @PostMapping("/recognize")
    public cmcar recognizeCar(@RequestParam("file") MultipartFile file) throws IOException {
        // 保存文件并调用百度API识别
        File tempFile = File.createTempFile("upload_", ".tmp");
        file.transferTo(tempFile);
        
        // 调用接口识别
        JSONObject apiResponse = recognitionService.recognizeCar(tempFile.getAbsolutePath());
        //System.out.print(apiResponse);
        
        //获取颜色
        String color = apiResponse.getString("color_result");
        
        //第0个结果-得分最高的结果
        JSONObject firseJsonObject = apiResponse.getJSONArray("result").getJSONObject(0);
        System.out.print(firseJsonObject);
        
        //返回对象类
        cmcar car  = new cmcar();
        car.setKind(firseJsonObject.getString("name"));
        car.setYear(firseJsonObject.getString("year"));
        car.setColor(color);
        return car;
    }

}
