package com.songzhen.howcool;

import cn.hutool.core.lang.UUID;
import com.songzhen.howcool.biz.UserBizService;
import com.songzhen.howcool.service.HBaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HowCoolApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(HowCoolApplicationTests.class);

	@Autowired
	private UserBizService userBizService;

	@Autowired
	private HBaseService hbaseService;

	@Test
	public void contextLoads() {

	}

	@Test
	public void userRegister() {
		userBizService.addUser(null,null,null,null);
	}

	@Test
	public void userLogin() {
		Map<String, Object> login = userBizService.login("zhangsan001", "52C69E3A57331081823331C4E69D3F2E", UUID.randomUUID().toString().replace("-", ""));
		logger.info("login res {}",login);
	}

	/**
	 * 测试删除、创建表
	 */
	@Test
	public void testCreateTable() {
		//删除表
		hbaseService.deleteTable("t_track");

		//创建表
		hbaseService.createTableBySplitKeys("t_track", Arrays.asList("info"),hbaseService.getSplitKeys(null));

		//插入三条数据
		hbaseService.putData("t_track","66804_000001","info",new String[]{"project_id","varName","coefs","pvalues","tvalues","create_time"},new String[]{"40866","mob_3","0.9416","0.0000","12.2293","null"});
		hbaseService.putData("t_track","66804_000002","info",new String[]{"project_id","varName","coefs","pvalues","tvalues","create_time"},new String[]{"40866","idno_prov","0.9317","0.0000","9.8679","null"});
		hbaseService.putData("t_track","66804_000003","info",new String[]{"project_id","varName","coefs","pvalues","tvalues","create_time"},new String[]{"40866","education","0.8984","0.0000","25.5649","null"});

		//查询数据
		//1. 根据rowKey查询
		Map<String,String> result1 = hbaseService.getRowData("t_track","66804_000001");
		System.out.println("+++++++++++根据rowKey查询+++++++++++");
		result1.forEach((k,value) -> {
			System.out.println(k + "---" + value);
		});
		System.out.println();

		//精确查询某个单元格的数据
		String str1 = hbaseService.getColumnValue("t_track","66804_000002","info","varName");
		System.out.println("+++++++++++精确查询某个单元格的数据+++++++++++");
		System.out.println(str1);
		System.out.println();

		//2. 遍历查询
		Map<String,Map<String,String>> result2 = hbaseService.getResultScanner("t_track");
		System.out.println("+++++++++++遍历查询+++++++++++");
		result2.forEach((k,value) -> {
			System.out.println(k + "---" + value);
		});
	}

}
