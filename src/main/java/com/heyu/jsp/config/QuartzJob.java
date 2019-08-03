package com.heyu.jsp.config;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.heyu.jsp.service.SupportService;

import lombok.extern.slf4j.Slf4j;

/**
 * 定时调度类
 * 
 * @author 孙贺宇
 * @version 1.0
 *
 */
@Slf4j
@Configuration
@EnableScheduling
public class QuartzJob {

	private Lock lock = new ReentrantLock();

	@Autowired
	private SupportService supportService;

	// @Scheduled(fixedRate = 60000)
	public void reloadCache() {
		log.info("reloadCache的任务调度！！！");
		if (!lock.tryLock()){
			return;
		}
		try {
			supportService.doReloadCache();
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			lock.unlock();
		}
		log.info("reloadCache的任务调度结束！！！");
	}

}
