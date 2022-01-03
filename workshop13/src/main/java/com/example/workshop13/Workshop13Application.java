package com.example.workshop13;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.nio.file.attribute.PosixFilePermissions;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.Files;

@SpringBootApplication
public class Workshop13Application {
	private static final Logger logger = LoggerFactory.getLogger(Workshop13Application.class);
	private static String dataDir = null;

	public static void main(String[] args) {
		logger.info("Args ::  " + args);
		for (int i = 0; i < args.length; i++) {
			logger.info("" + args[i]);
			if (args[i].contains("--dataDir=")) {
				dataDir = args[i].substring(10, args[i].length());
				logger.info("From CLI argument > " + dataDir);
			}

		}
		try {
			File dir = new File(dataDir);
			dir.mkdirs();
			String perm = "rwxrwx---";// in octal = 770
			Set<PosixFilePermission> permissions = PosixFilePermissions.fromString(perm);
			Files.setPosixFilePermissions(dir.toPath(), permissions);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		SpringApplication.run(Workshop13Application.class, args);
	}

}
