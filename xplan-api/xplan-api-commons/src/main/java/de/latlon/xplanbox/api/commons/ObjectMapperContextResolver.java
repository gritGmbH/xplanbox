package de.latlon.xplanbox.api.commons;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.ext.ContextResolver;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Common ContextResolver specifying the date format and time zone.
 */
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

    private final ObjectMapper mapper;

    public ObjectMapperContextResolver() {
        this.mapper = createObjectMapper();
    }

    @Override
    public ObjectMapper getContext( Class<?> type ) {
        return mapper;
    }

    private ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat( new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss.SSSZ" ) );
        mapper.setTimeZone( TimeZone.getDefault() );
        return mapper;
    }
}