package com.quebic.searchbox.config;

import javax.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="searchbox")
public class SearchBoxConfig {

	@NotNull
	private String appname;
	
	private DataLoader dataloader = new DataLoader();
	
	private PageConfig page = new PageConfig();

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public DataLoader getDataloader() {
		return dataloader;
	}

	public void setDataloader(DataLoader dataloader) {
		this.dataloader = dataloader;
	}

	public PageConfig getPage() {
		return page;
	}

	public void setPage(PageConfig page) {
		this.page = page;
	}
	
}
