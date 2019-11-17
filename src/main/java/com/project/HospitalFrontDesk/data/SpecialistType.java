package com.project.HospitalFrontDesk.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SpecialistType {
 
	private String type;
	private String name;
	private String availableDays;
	private String availableTime;
	//private boolean isAvailable;
	private String availability;
	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	private String hospitalId;
	
	public SpecialistType() {
		
	}
	
	public SpecialistType(String type, String name, String availableDays, String availableTime, String availability,
			String hospitalId) {
		this.type = type;
		this.name = name;
		this.availableDays = availableDays;
		this.availableTime = availableTime;
		this.availability = availability;
		this.hospitalId = hospitalId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvailableDays() {
		return availableDays;
	}

	public void setAvailableDays(String availableDays) {
		this.availableDays = availableDays;
	}

	public String getAvailableTime() {
		return availableTime;
	}

	public void setAvailableTime(String availableTime) {
		this.availableTime = availableTime;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	@Override
	public String toString() {
		return "SpecialistType [type=" + type + ", name=" + name + ", availableDays=" + availableDays
				+ ", availableTime=" + availableTime + ", availability=" + availability + ", hospitalId=" + hospitalId
				+ "]";
	}
}