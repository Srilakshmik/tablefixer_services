package com.ivo.app.services.service;

import com.ivo.app.services.entity.Cuisine;
import com.ivo.app.services.entity.EventPayPrefRef;
import com.ivo.app.services.entity.EventPurposeRef;
import com.ivo.app.services.entity.GenderCatgRef;

import java.util.List;

public interface ReferenceDataService {

    List<EventPayPrefRef> getAllGuestPayPreferences();

    List<GenderCatgRef> getAllEventGenderPreferences();

    List<EventPurposeRef> getAllEventPurposes();

    List<Cuisine> getCuisinesList();

    List<GenderCatgRef> getAllGendersList();
}
