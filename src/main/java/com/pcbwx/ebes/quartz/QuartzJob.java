package com.pcbwx.ebes.quartz;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 定时调度类
 * @author 孙贺宇
 * @version 1.0
 *
 */
@Configuration
@EnableScheduling
public class QuartzJob {
	
	private Logger logger = LoggerFactory.getLogger(QuartzJob.class);

	private static AtomicInteger reloadFlag = new AtomicInteger();
	@Scheduled(fixedRate = 5 * 1000) // 5秒
	public void reloadCache() {
		logger.info("reloadCache的任务调度！！！");
		if (reloadFlag.incrementAndGet() > 1) {
			reloadFlag.decrementAndGet();
			return;
		}
		
		logger.error("测试error级别日志");
		reloadFlag.decrementAndGet();		
		logger.info("reloadCache的任务调度结束！！！");
	}

	private static AtomicInteger planGenerateFlag = new AtomicInteger();	
	@Scheduled(cron = "0 0/1 * ? * *") // 1分钟
	public void generateExePlan() {
		if (planGenerateFlag.incrementAndGet() > 1) {
			planGenerateFlag.decrementAndGet();
			return;
		}

		planGenerateFlag.decrementAndGet();	
	}

	private static AtomicInteger makePlanWindowFlag = new AtomicInteger();
	@Scheduled(fixedRate = 1 * 60 * 1000) // 1分钟
	public void makePlanOneWoindow() {
		if (makePlanWindowFlag.incrementAndGet() > 1) {
			makePlanWindowFlag.decrementAndGet();
			return;
		}
		makePlanWindowFlag.decrementAndGet();			
	}
}