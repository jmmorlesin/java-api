package com.jmms.api.core;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;
import com.jmms.api.utils.Routes;


@Component
class CustomizationBean implements EmbeddedServletContainerCustomizer{

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setContextPath(Routes.CONTEXT_PATH);
    }

}
