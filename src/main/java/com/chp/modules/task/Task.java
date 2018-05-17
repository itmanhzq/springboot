package com.chp.modules.task;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling // 启用定时任务
public class Task {
  
  protected final Logger logger =  LoggerFactory.getLogger(this.getClass());
  
  @Scheduled(cron="0 0/1 * * * ?")
	public void run(){
		logger.info(">>>Scheduled Running...");
	}
}
