package com.project.HospitalFrontDesk.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.HospitalFrontDesk.config.SpecialistConfig;
import com.project.HospitalFrontDesk.data.AppointmentResponse;
import com.project.HospitalFrontDesk.data.HospitalPatientStatus;
import com.project.HospitalFrontDesk.data.SpecialistType;
import com.project.HospitalFrontDesk.exception.BusinessServiceException;
import com.project.HospitalFrontDesk.exception.InvalidSpecialistException;

@RestController
@RequestMapping("/")
//@Configuration
//@PropertySource(value="classpath:specialist.properties")
public class HospitalFrontDeskController {
	
	/*@Autowired
	public AppConfig appConfig;
	
	String specialistUrl = null;*/
	private SpecialistConfig specialistConfig;
	@Autowired
    public void setApp(SpecialistConfig specialistConfig) {
        this.specialistConfig = specialistConfig;
    }
	
	private Map<String, String> currentHealth = Collections.singletonMap("status", "UP");
	
	
	@RequestMapping(
			  value = "/{hospitalName}/{specialistType}", 
			  method = RequestMethod.GET,
			  produces = { "application/json"}
			)
	public ResponseEntity<List<SpecialistType>> retrieveSpecialistDetails(@PathVariable ("hospitalName") String hospitalName, 
			@PathVariable ("specialistType") String speciatlistType) {
		
		List<SpecialistType> specialistTypes = new ArrayList<>();
		List<SpecialistType> selectedSpecialist = new ArrayList<>();
		
		if(null != specialistConfig) {
			specialistTypes = specialistConfig.getSpecialist();	
		}
			Iterator<SpecialistType> itr = specialistTypes.iterator();
			
			while(itr.hasNext()) {
				SpecialistType specialistdetails = itr.next();
				System.out.println(hospitalName);
				System.out.println(specialistdetails.getHospitalId());
				
				
				if(null != specialistdetails && specialistdetails.getHospitalId().equalsIgnoreCase(hospitalName)) {
					
					if(null != specialistdetails.getType() && specialistdetails.getType().equalsIgnoreCase(speciatlistType)) {
						selectedSpecialist.add(specialistdetails);
					}
					
				}else {
					//System.out.println("hospital name not found");
					 throw new BusinessServiceException("Hospital " + hospitalName +" not found !!");
				}
			}
			
			System.out.println(selectedSpecialist);
			
			if(selectedSpecialist.isEmpty()) {
				throw new InvalidSpecialistException("Specialist " + speciatlistType +" not available !!");
			}
			
		return ResponseEntity.ok(selectedSpecialist);//specialistTypes;
	}
	
	@RequestMapping(value = "/{specialistName}/{appointmentDay}/{patientName}", 
			  method = RequestMethod.GET,
			  produces = { "application/json"})
	public List<AppointmentResponse> getAppointment(@PathVariable ("specialistName") String specialistName, 
			@PathVariable ("appointmentDay") String appointmentDay, @PathVariable ("patientName")String patientName){
		
		List<AppointmentResponse> finalAppointmentList = new ArrayList<>();
		//List<String> selectedSpecialistList = new ArrayList<>();
		AppointmentResponse appointmentResponse = null;
		List<SpecialistType> listFromConfig = new ArrayList<>();
		String [] specialistAvailableDays = null;
		
		if(null != specialistConfig) {
			listFromConfig = specialistConfig.getSpecialist();	
		}
		
		Iterator<SpecialistType> itr = listFromConfig.iterator();
		
		while(itr.hasNext()) {
			
			SpecialistType specialistType = itr.next();
			
			specialistAvailableDays = specialistType.getAvailableDays().split(",");
			System.out.println(specialistAvailableDays.length);
			
			if (null != specialistType && specialistType.getName().equalsIgnoreCase(specialistName))
			{
				System.out.println("here");
				if(null != specialistAvailableDays) {
					for(int i=0; i< specialistAvailableDays.length;i++) {
						if(specialistAvailableDays[i].equalsIgnoreCase(appointmentDay)) {
							appointmentResponse = new AppointmentResponse();
							appointmentResponse.setSpecialistName(specialistName);
							appointmentResponse.setApoointmentDay(appointmentDay);
							appointmentResponse.setPatientName(patientName);
							appointmentResponse.setAppointmentTime(specialistType.getAvailableTime());
						}else {
							throw new InvalidSpecialistException("Specialist "+specialistType.getType()+" not available on "+appointmentDay);
						}
					}
				}
				finalAppointmentList.add(appointmentResponse);
			}else {
				throw new InvalidSpecialistException("Specialist "+specialistType.getType()+" not available.");
			}
		}
		
		System.out.println(finalAppointmentList);
		
		return finalAppointmentList;
	}
	
	
	@RequestMapping(value = "/{hospitalName}", 
			  method = RequestMethod.GET, produces= {"application/json"})
	public String getNumberOfAvailableBeds(@PathVariable ("hospitalName") String hospitalName) {
		
		Integer numOfBedsAvailable = 0;
		List<HospitalPatientStatus> patientDetails = getPatientDetails();
		
		Iterator<HospitalPatientStatus> hospItr = patientDetails.iterator();
		
		while(hospItr.hasNext()) {
			HospitalPatientStatus hospitalPatientStatus = hospItr.next();
			if(null != hospitalPatientStatus && hospitalPatientStatus.getHospitalName().equals(hospitalName)) {
				if(hospitalPatientStatus.getPatientStatus().equals("DISCHARGE")) {
					numOfBedsAvailable += 1;
				}	
			}else {
				throw new BusinessServiceException("Hospital " + hospitalName +" not found !!");
			}
		}
		return "Number of Beds Available is = "+numOfBedsAvailable;
	}
	
	private List<HospitalPatientStatus> getPatientDetails() {
		return Arrays.asList(new HospitalPatientStatus("946", "Patient1", "DISCHARGE"),
							 new HospitalPatientStatus("946", "Patient2", "ADMITTED"),
							 new HospitalPatientStatus("946", "Patient3", "DISCHARGE"),
							 new HospitalPatientStatus("946", "Patient4", "ADMITTED"),
							 new HospitalPatientStatus("946", "Patient5", "ADMITTED"),
							 new HospitalPatientStatus("946", "Patient6", "ADMITTED"),
							 new HospitalPatientStatus("946", "Patient7", "DISCHARGE"),
							 new HospitalPatientStatus("946", "Patient8", "ADMITTED"),
							 new HospitalPatientStatus("946", "Patient9", "ADMITTED"),
							 new HospitalPatientStatus("946", "Patient10", "DISCHARGE"),
							 new HospitalPatientStatus("946", "Patient11", "ADMITTED"),
							 new HospitalPatientStatus("946", "Patient12", "DISCHARGE")
							 );
	}

	@RequestMapping(value = "/health", method = RequestMethod.GET, produces = {"application/json"})
	public Map<String, String> getHealth(){
		return currentHealth;
	}
}
