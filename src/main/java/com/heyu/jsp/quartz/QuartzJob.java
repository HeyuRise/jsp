package com.heyu.jsp.quartz;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.heyu.jsp.service.SupportService;

import lombok.extern.slf4j.Slf4j;

/**
 * 定时调度类
 * @author 孙贺宇
 * @version 1.0
 *
 */
@Slf4j
@Configuration
@EnableScheduling
public class QuartzJob {
	
	@Autowired
	private SupportService supportService;
	
	private static AtomicInteger reloadFlag = new AtomicInteger();
	// @Scheduled(fixedRateString = "${reload.timer.fixedRate}")
	public void reloadCache() {
		log.info("reloadCache的任务调度！！！");
		if (reloadFlag.incrementAndGet() > 1) {
			reloadFlag.decrementAndGet();
			return;
		}
		try {
			supportService.doReloadCache();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		reloadFlag.decrementAndGet();
		log.info("reloadCache的任务调度结束！！！");
	}

	private static AtomicInteger planGenerateFlag = new AtomicInteger();	
	// @Scheduled(cron = "${plan.generate.timer.corn}")
	public void generateExePlan() {
		log.info("planGenerate的任务调度！！！");
		if (planGenerateFlag.incrementAndGet() > 1) {
			planGenerateFlag.decrementAndGet();
			return;
		}

		planGenerateFlag.decrementAndGet();
		log.info("planGenerate的任务调度结束！！！");
	}
	
}
