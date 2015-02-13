package com.thoersch.seeds.init;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class ObjectMapperResolver implements ContextResolver<ObjectMapper> {
    @Autowired
    private ObjectMapper mapper;

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return mapper;
    }
}