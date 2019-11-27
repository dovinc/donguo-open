package pers.donguo.open;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * <p>Title: DonguoOpenApplication.java </p>
 * <p>Description: springBoot 主启动类</p>
 * @author Penguin
 * @date 2019年11月16日
 * @version 1.0
 */
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启spring Security 方法级权限认证注解
public class DonguoOpenApplication {

	public static void main(String[] args) {
		SpringApplication.run(DonguoOpenApplication.class, args);
	}
}
