package com.ivo.app.services.controller;

import com.ivo.app.services.entity.Cuisine;
import com.ivo.app.services.entity.EventPayPrefRef;
import com.ivo.app.services.entity.EventPurposeRef;
import com.ivo.app.services.entity.GenderCatgRef;
import com.ivo.app.services.service.ReferenceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/ref")
public class ReferenceDataController {
	
	@Autowired
	private ReferenceDataService referenceDataService;

	@GetMapping(value = "/event/guests/paypref/list")
    public List<EventPayPrefRef> getAllGuestPayPreferences() {
		return referenceDataService.getAllGuestPayPreferences();
	}

    @GetMapping(value = "/event/genders/catg/list")
    public List<GenderCatgRef> getAllEventGenderPreferences() {
		return referenceDataService.getAllEventGenderPreferences();
	}

    @GetMapping(value = "/genders/list")
	public List<GenderCatgRef> getGendersList() {
        return referenceDataService.getAllGendersList();
    }

	@GetMapping(value="/eventspurpose/list")
    public List<EventPurposeRef> getAllEventPurposes() {
		return referenceDataService.getAllEventPurposes();
	}
	
	@GetMapping(value="/cusine/list/top/{count}")
    public List<Cuisine> getCuisinesList() {
        return referenceDataService.getCuisinesList();
	}
}
