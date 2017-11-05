package com.jmms.api.core.filter;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.glassfish.jersey.server.ContainerRequest;
import com.jmms.api.utils.Constants;
import com.jmms.api.utils.StringUtils;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


@PreMatching
@Provider
public class AuthenticationRequestFilter implements ContainerRequestFilter {

    private final static Logger logger = Logger.getLogger(AuthenticationRequestFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String requestId = StringUtils.getUUID();
        MDC.put(Constants.REQUEST_ID_KEY, requestId);

        logger.info(
                StringUtils.join(
                        "Id: ", requestId,
                        ", Request: ", ((ContainerRequest) requestContext).getRequestUri().toString(),
                        ", Method: ", requestContext.getMethod(),
                        ", Media Type: ", StringUtils.getStringNotNull(requestContext.getMediaType()),
                        ", Headers: (", StringUtils.generateStringFromMap(requestContext.getHeaders()),
                        ")"
                )
        );

    }

}
