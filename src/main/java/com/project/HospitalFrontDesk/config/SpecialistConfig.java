package com.project.HospitalFrontDesk.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.project.HospitalFrontDesk.data.SpecialistType;

@Component
@PropertySource("classpath:specialist.properties")
//@ConfigurationProperties(prefix = “myprefix”)
@ConfigurationProperties(prefix="app")
public class SpecialistConfig {

	private List<SpecialistType> specialist;

	public List<SpecialistType> getSpecialist() {
		return specialist;
	}

	public void setSpecialist(List<SpecialistType> specialist) {
		this.specialist = specialist;
	}
}
