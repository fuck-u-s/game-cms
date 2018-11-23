package com.cms.utils;

import org.slf4j.LoggerFactory;

public class Logger {

	public static final org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);

	public static void info(Object message) {
		logger.info("info",message);
	}

	public static void debug(Object message) {
		logger.debug("debug",message);
	}

	public static void error(Object message) {
		logger.error("error",message);
	}

	public static void error(String s, Exception e) {
		logger.error(s,e);
	}
}
