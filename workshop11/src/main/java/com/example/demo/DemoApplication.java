package com.example.demo;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class DemoApplication implements ApplicationRunner {
	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	private static String portNumber = null;

	private static final String DEFAULT_PORT = "3000";

	public static void main(String[] args) {
		logger.info("Args ::  " + args);
		for (int i = 0; i < args.length; i++) {
			logger.info("" + args[i]);
			if (args[i].contains("--port=")) {
				portNumber = args[i].substring(args[i].length() - 4, args[i].length());
				logger.info("From CLI argument > " + portNumber);
			}

		}
		logger.info("before.. " + portNumber);
		if (portNumber == null) {
			portNumber = System.getenv("PORT");
			logger.info("From Env Var argument > " + portNumber);
		}

		if (portNumber == null) {
			portNumber = DEFAULT_PORT;
		}
		logger.info("From PORT final > " + portNumber);
		SpringApplication app = new SpringApplication(DemoApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", portNumber));
		app.run(args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List portValues = args.getOptionValues("port");
		if (portValues != null && !portValues.isEmpty()) {
			for (int i = 0; i < portValues.size(); i++) {
				logger.info("" + portValues.get(i));
			}
		}
	}
}
