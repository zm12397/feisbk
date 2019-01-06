package com.zfm.feisbk;

import com.zfm.feisbk.dao.*;
import com.zfm.feisbk.pojo.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashSet;

/**
 * Sprng Boot项目入口函数
 * @author zm
 *
 */
@SpringBootApplication
@EnableNeo4jRepositories(basePackages = {"com.zfm.feisbk.dao"})
public class App extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(App.class);
	}

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
//		ToFollowDao toFollowDao = context.getBean(ToFollowDao.class);
//		toFollowDao.deleteByStartAndEnd(96l,76l);
//		UserDao userDao = context.getBean(UserDao.class);

//		System.out.println(user);
//		插入节点：
//		UserDO user = new UserDO();
//		user.setUsername("test");
//		user.setPassword("345");
//		userDao.save(user);
//		插入节点：
//		BlogDO blog = new BlogDO();
//		blog.setContentTest("aaaa");
//		BlogDao blogDao = context.getBean(BlogDao.class);
//		blogDao.save(blog);
//		创建关系：
//		PublishDO publishDO = new PublishDO(user,blog);
//		PublishDao publishDao = context.getBean(PublishDao.class);
//		publishDao.save(publishDO);

//		关注一个用户——zfm关注wlf：
//		UserDO zfm = userDao.findOne(76l);
//		UserDO wlf = userDao.findOne(96l);
//		TofollowDO tofollow = new TofollowDO(zfm,wlf);
//		ToFollowDao toFollowDao = context.getBean(ToFollowDao.class);
//		toFollowDao.save(tofollow);
//		BefollowDO befollow = new BefollowDO(wlf,zfm);
//		BeFollowDao beFollowDao = context.getBean(BeFollowDao.class);
//		beFollowDao.save(befollow);

//		UserDO wlf = userDao.findOne(96l);
//		System.out.println(wlf);
//		UserDO user = new UserDO();
//		user.setUsername("volleyfo");
//		user.setPassword("123");
//		userDao.save(user);
//		UserDO user2 = new UserDO();
//		user2.setUsername("zfm");
//		user2.setPassword("123");
//		userDao.save(user2);
	}

}
