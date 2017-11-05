package com.jmms.api.core.filter;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.glassfish.jersey.server.ContainerRequest;
import com.jmms.api.utils.Constants;
import com.jmms.api.utils.StringUtils;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Provider
public class PostRequestFilter implements ContainerResponseFilter {

    private final static Logger logger = Logger.getLogger(PostRequestFilter.class);

    private final static Integer HTTP_CACHE_MAX_AGE = 15 * 60; //15 minutes

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {

        MultivaluedMap<String, Object> headers = responseContext.getHeaders();
        if (!headers.containsKey(HttpHeaders.CACHE_CONTROL)) {
            List<Object> values = new ArrayList<>();
            values.add("no-transform, max-age=" + HTTP_CACHE_MAX_AGE);
            headers.put(HttpHeaders.CACHE_CONTROL, values);
        }

        logger.info(
                StringUtils.join(
                        "Id: ", MDC.get(Constants.REQUEST_ID_KEY).toString(),
                        ", Request: ", ((ContainerRequest) requestContext).getRequestUri().toString(),
                        ", Response code: ", Integer.toString(responseContext.getStatusInfo().getStatusCode()),
                        ", Media Type: ", StringUtils.getStringNotNull(requestContext.getMediaType()),
                        ", Headers: (", StringUtils.generateStringFromMap((MultivaluedMap)headers),
                        ")"
                )
        );

    }

}
