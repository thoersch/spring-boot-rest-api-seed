package com.thoersch.seeds.init;


import org.glassfish.jersey.filter.LoggingFilter;

import java.util.logging.Logger;

public class LoggingInitialization extends JerseyInitialization {

    private static final int MAX_ENTITY_LOG_BYTES = 8 * 1024;

    public LoggingInitialization() {
        this.register(new LoggingFilter(Logger.getLogger("com.thoersch.seeds"), MAX_ENTITY_LOG_BYTES));
    }
}
