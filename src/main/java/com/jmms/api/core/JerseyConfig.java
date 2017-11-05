package com.jmms.api.core;

import com.jmms.api.controller.AuthenticatedController;
import com.jmms.api.core.GsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import com.jmms.api.controller.HealthController;
import com.jmms.api.controller.LoginController;
import com.jmms.api.core.filter.AuthenticationRequestFilter;
import com.jmms.api.core.filter.PostRequestFilter;
import com.jmms.api.utils.Config;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {

        //MessageBodyReader and MessageBodyWriter
        register(GsonProvider.class);

        //Filters
        register(AuthenticationRequestFilter.class);
        register(PostRequestFilter.class);

        //Controllers
        register(HealthController.class);
        register(LoginController.class);
        register(AuthenticatedController.class);

        //This is the way to respect the response code instead of 200 or 404 only
        //http://stackoverflow.com/questions/34318747/how-to-handle-spring-boots-redirection-to-error
        Map<String, Object> properties = new HashMap<>();
        properties.put("jersey.config.server.response.setStatusOverSendError", true);
        setProperties(properties);
    }

}