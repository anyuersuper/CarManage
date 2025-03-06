package com.carmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.carmanage.Mapper")
public class CarmanageApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarmanageApplication.class, args);
		System.out.print("启动啦！");
	}

}
