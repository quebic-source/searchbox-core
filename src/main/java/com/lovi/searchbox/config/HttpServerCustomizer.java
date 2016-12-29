package com.lovi.searchbox.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;

@Component
public class HttpServerCustomizer implements EmbeddedServletContainerCustomizer {

	private HttpServerConfig httpServerConfig;
	
	@Autowired
	public HttpServerCustomizer(HttpServerConfig httpServerConfig) {
		this.httpServerConfig = httpServerConfig;
	}
	
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(httpServerConfig.getPort());
	}

}
