package com.thoersch.seeds;

import com.thoersch.seeds.init.LoggingInitialization;
import com.thoersch.seeds.init.filters.ApiOriginFilter;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.AuthenticationManagerConfiguration;
import org.springframework.boot.autoconfigure.security.FallbackWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SpringBootWebSecurityConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

import javax.inject.Inject;
import java.util.Properties;

@Configuration
@EnableAutoConfiguration(exclude = {AuthenticationManagerConfiguration.class, FallbackWebSecurityAutoConfiguration.class, SecurityAutoConfiguration.class, SpringBootWebSecurityConfiguration.class})
@ComponentScan
public class Application {
    @Inject
    private ApplicationConfiguration configuration;

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("org.apache.tomcat.util.http. ServerCookie.STRICT_NAMING", "false");
        properties.setProperty("org.apache.tomcat.util.http.ServerCookie.ALLOW_HTTP_SEPARATORS_IN_V0", "true");

        new SpringApplicationBuilder(Application.class).properties(properties).showBanner(false).run(args);
    }

    @Bean
    public ServletRegistrationBean jerseyServlet() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/*");

        registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, LoggingInitialization.class.getName());

        return registration;
    }

    /**
     * Keeps the session open until the end of a request. Allows us to use lazy-loading with Hibernate.
     */
    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
        return new OpenEntityManagerInViewFilter();
    }

    @Bean
    public ApiOriginFilter apiOriginFilter() {
        return new ApiOriginFilter();
    }
}
