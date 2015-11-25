/**
 * 
 */
package com.sample.rest.user.registration.annotation;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;

/**
 * @author maruthishanmugam
 *
 */
@Aspect
public class UDEAspectTracer {

	
	private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UDEAspectTracer.class);

	private static final String OPEN_BRACKET = "[";

	private static final String CLOSE_BRACKET = "]";

	private static final String TAB = " ";

	private static final String METHOD_ENTERED = "Method Entry";

	private static final String METHOD_EXIT = "Method Exit";

	@SuppressWarnings("all")
	@Around(value = "@annotation(com.sample.rest.user.registration.annotation.UDETrace)")
	public Object monitor(ProceedingJoinPoint joinpoint) throws Throwable {

		boolean exitPresent = false;
		boolean timerPresent = false;
		boolean exceptionPresent = false;
		long startTime = System.currentTimeMillis();
		StringBuilder builder = new StringBuilder();
		Class classz = joinpoint.getTarget().getClass();
		builder.append(new Date().toString());
		builder.append(TAB);
		builder.append(OPEN_BRACKET);
		builder.append(classz.getName());
		builder.append(CLOSE_BRACKET);
		try {
			Method[] methods = classz.getMethods();
			for (Method method : methods) {
				if (method.isAnnotationPresent(UDETrace.class)) {
					UDETrace trace = method.getAnnotation(UDETrace.class);
					String methodName = method.getName();
					builder.append(TAB);
					builder.append(methodName);
					builder.append(TAB);
					builder.append(trace.informationGroupTag());
					builder.append(TAB);
					builder.append(trace.information());
					builder.append(TAB);
					for (LogType type : trace.logTypes()) {
						if (type.compareTo(LogType.ENTRY_EXIT) == 0) {
							exitPresent = true;
						}
						if (type.compareTo(LogType.TIMER) == 0) {
							timerPresent = true;
						}
						if (type.compareTo(LogType.EXCEPTION) == 0) {
							exceptionPresent = true;
						}

					}
				}
			}
			if (exitPresent) {
				logEntry(builder);
			}
			Object object = joinpoint.proceed();
			if (exitPresent) {
				logExit(builder);
			}
			if (timerPresent) {
				logTimer(builder, startTime);
			}

			return object;

		} catch (Throwable exception) {
			if (exceptionPresent) {
				LOGGER.error("Exception occured on the Target", exception);
			}
			if (timerPresent) {
				logTimer(builder, startTime);
			}
			throw exception;
		}
	}

	private void logEntry(StringBuilder message) {
		LOGGER.info(message.toString() + TAB + METHOD_ENTERED);
	}

	private void logExit(StringBuilder message) {
		LOGGER.info(message.toString() + TAB + METHOD_EXIT);
	}

	private void logTimer(StringBuilder message, long startTime) {
		long procTime = System.currentTimeMillis() - startTime;
		LOGGER.info(message.toString() + TAB + "Time Taken(milliseconds) : " + procTime);
	}

	
}
