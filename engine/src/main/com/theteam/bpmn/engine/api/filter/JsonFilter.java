package com.theteam.bpmn.engine.api.filter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;


import org.apache.commons.io.IOUtils;

/*
@Provider
public class JsonFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext request) {
        if (isJson(request)) {
            try {
                String json = IOUtils.toString(request.getEntityStream(), StandardCharsets.UTF_8);
                // do whatever you need with json



                // replace input stream for Jersey as we've already read it
                InputStream in = IOUtils.toInputStream(json, StandardCharsets.UTF_8);
                request.setEntityStream(in);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    boolean isJson(ContainerRequestContext request) {
        // define rules when to read body
        return request.getMediaType().toString().contains("application/json"); 
    }

}

*/