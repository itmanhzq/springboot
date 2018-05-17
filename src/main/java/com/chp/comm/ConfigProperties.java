package com.chp.comm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component("configPropertiesFactory")
public class ConfigProperties {
	private String appshortname;
	@Autowired
	private Environment env;

	@Bean
	public ConfigProperties configProperties() {
		ConfigProperties properties = new ConfigProperties();
		this.appshortname = this.env.getProperty("config.appshortname");
		properties.setAppshortname(this.appshortname);
		return properties;
	}

	public String getAppshortname() {
		return this.appshortname;
	}

	public void setAppshortname(String appshortname) {
		this.appshortname = appshortname;
	}

}