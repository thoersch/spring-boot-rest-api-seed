package com.thoersch.seeds.init;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonSerializerInitialization {
    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.INDENT_OUTPUT, true)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .registerModule(new JSR310Module());
    }

    @Bean
    public ObjectReader getObjectReader() {
        return getObjectMapper().reader();
    }

    @Bean
    public ObjectWriter getObjectWriter() {
        return getObjectMapper().writer();
    }
}
