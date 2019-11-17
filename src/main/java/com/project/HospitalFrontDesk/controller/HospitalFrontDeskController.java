package com.project.HospitalFrontDesk.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.HospitalFrontDesk.config.SpecialistConfig;
import com.project.HospitalFrontDesk.data.SpecialistType;
import com.project.HospitalFrontDesk.exception.BusinessServiceException;
import com.project.HospitalFrontDesk.exception.InvalidSpecialistException;

@RestController
@RequestMapping("/hospital")
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
					System.out.println("hospital name not found");
					 throw new BusinessServiceException("Hospital " + hospitalName +" not found !!");
				}
			}
			
			System.out.println(selectedSpecialist);
			
			if(selectedSpecialist.isEmpty()) {
				throw new InvalidSpecialistException(speciatlistType);
			}
			
		return ResponseEntity.ok(selectedSpecialist);//specialistTypes;
	}
}
