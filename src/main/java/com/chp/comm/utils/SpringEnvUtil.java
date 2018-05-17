package com.chp.comm.utils;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SpringEnvUtil implements EnvironmentAware {
	
	private static final String EGSC_CONFIG_KEY_PERFIX = "config.";
	
	private Environment environment;

	
	public String getProperty(String key) {
		RelaxedPropertyResolver rResolver = new RelaxedPropertyResolver(this.getEnvironment(), EGSC_CONFIG_KEY_PERFIX);
		return rResolver.getProperty(key);
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public Environment getEnvironment() {
		return environment;
	}

}
