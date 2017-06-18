package com.quebic.searchbox.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HttpServerConfig {

	@Value("${server.host:localhost}")
	private String host;
	
	@Value("${server.port:1028}")
	private int port;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
}
