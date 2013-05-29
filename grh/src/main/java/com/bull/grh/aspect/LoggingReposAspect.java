package com.bull.grh.aspect;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;

/**
 * @author simu
 */
@Aspect
public class LoggingReposAspect extends CustomizableTraceInterceptor {
    protected final Logger logger = Logger.getLogger("Repository LOG");

    @Override
    protected void writeToLog(Log logger, String message, Throwable ex) {
        if (ex != null) {
            this.logger.info(message, ex);
        } else {
            this.logger.info(message);
        }
    }

    @Override
    protected boolean isLogEnabled(Log logger) {
        return true;
    }
}
