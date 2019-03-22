package com.songzhen.howcool;

import cn.hutool.core.lang.UUID;
import com.songzhen.howcool.biz.UserBizService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HowcoolApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(HowcoolApplicationTests.class);

	@Autowired
	private UserBizService userBizService;

	@Test
	public void contextLoads() {

	}

	@Test
	public void userRegister() {
		userBizService.addUser();
	}

	@Test
	public void userLogin() {
		Map<String, Object> login = userBizService.login("17211111111", "52C69E3A57331081823331C4E69D3F2E", UUID.randomUUID().toString().replace("-", ""));
		logger.info("login res {}",login);
	}

}
