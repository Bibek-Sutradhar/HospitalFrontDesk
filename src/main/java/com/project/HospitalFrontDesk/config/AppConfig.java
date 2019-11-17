package com.project.HospitalFrontDesk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {
	
	@Value("${hospital.specialist}")
	private String appURL;
	
	public String getAppURL() {
		return appURL;
	}
	
	public void setAppURL(String appURL) {
		this.appURL = appURL;
	}

}
