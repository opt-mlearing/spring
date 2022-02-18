package org.framework.example.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * procedure working state trace info.
 *
 * @author caogaoli
 * @date 2022/2/18上午10:58
 */
@Slf4j
public class StackTracePrintTools {

	// print Invoker stack message.
	public static void printInvokerStack() {
		final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		log.debug("stack trace length {}", stackTrace.length);
		for (int index = stackTrace.length - 1; index >= 0; --index) {
			StackTraceElement stackTraceElement = stackTrace[index];
			log.debug("--Invoker CLass{} -- Method {}", stackTraceElement.getClassName(), stackTraceElement.getMethodName());
		}
	}

}
