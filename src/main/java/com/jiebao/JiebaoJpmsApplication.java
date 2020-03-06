
package com.jiebao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//@MapperScan("com.jiebao.jpmp.mapper")
public class JiebaoJpmsApplication extends SpringBootServletInitializer {


	public static void main(String[] args) {
		SpringApplication.run(JiebaoJpmsApplication.class);
	}
}
