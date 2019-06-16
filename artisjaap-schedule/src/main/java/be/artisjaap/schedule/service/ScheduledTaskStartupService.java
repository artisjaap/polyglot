package be.artisjaap.schedule.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

@Service
public class ScheduledTaskStartupService {

	@Resource
    private ScheduledTaskService scheduledTaskService;

	@PostConstruct
	void startGeplandeTaken() {
		scheduledTaskService.startGeplandeTaken();
	}

	@PreDestroy
	void closeScheduler() {
		scheduledTaskService.stopTaskService();
	}

}
