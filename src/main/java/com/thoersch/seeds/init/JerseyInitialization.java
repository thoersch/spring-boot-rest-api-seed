package com.thoersch.seeds.init;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class JerseyInitialization extends ResourceConfig {
    public JerseyInitialization() {
        this.register(RequestContextFilter.class);
        this.register(ObjectMapperResolver.class);
        this.register(RolesAllowedDynamicFeature.class);
        this.register(new JacksonJsonProvider(ObjectMapperFactory.create()));

        this.property(ServerProperties.TRACING, "ALL"); //can set to ON_DEMAND
        this.property(ServerProperties.TRACING_THRESHOLD, "SUMMARY"); //SUMMARY, TRACE, VERBOSE
        this.property(ServerProperties.MOXY_JSON_FEATURE_DISABLE, "true");
        this.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, "true");
        this.packages("com.thoersch.seeds.resources");
    }
}
