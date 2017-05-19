package com.snapscreenapp.supervisor;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitoringController {

	private static final String template = "Monitoring deviceId: %s";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping(value="/monitor", method=POST)
	public Activity greeting(@RequestParam(value = "deviceId") String deviceId) {
		return new Activity(counter.incrementAndGet(), String.format(template, deviceId));
	}
}