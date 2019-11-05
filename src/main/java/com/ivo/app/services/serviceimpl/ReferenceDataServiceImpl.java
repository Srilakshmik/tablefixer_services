package com.ivo.app.services.serviceimpl;

import com.ivo.app.services.entity.Cuisine;
import com.ivo.app.services.entity.EventPayPrefRef;
import com.ivo.app.services.entity.EventPurposeRef;
import com.ivo.app.services.entity.GenderCatgRef;
import com.ivo.app.services.repository.CuisinesRepository;
import com.ivo.app.services.repository.EventGenderPrefRepository;
import com.ivo.app.services.repository.EventPayPrefRepository;
import com.ivo.app.services.repository.EventPurposeRefRepository;
import com.ivo.app.services.service.ReferenceDataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenceDataServiceImpl implements ReferenceDataService {
	private static final Logger logger = LogManager.getLogger(ReferenceDataServiceImpl.class);

	@Autowired
	private EventGenderPrefRepository eventGenderPrefRepository;

	@Autowired
	private EventPayPrefRepository eventPayPrefRepository;

	@Autowired
	private CuisinesRepository cuisinesRepository;

	@Autowired
	private EventPurposeRefRepository eventPurposeRefRepository;

	@Override
	@Cacheable(cacheNames = "payPrefsList")
	public List<EventPayPrefRef> getAllGuestPayPreferences() {
		return eventPayPrefRepository.findAllByActiveTrueOrderByOrderBy();
	}

	@Override
	@Cacheable(cacheNames = "eventGenderPrefList")
	public List<GenderCatgRef> getAllEventGenderPreferences() {
		return eventGenderPrefRepository.findAllByActiveEventTypeTrueOrderByOrderBy();
	}

	@Override
	@Cacheable(cacheNames = "gendersList")
	public List<GenderCatgRef> getAllGendersList() {
		return eventGenderPrefRepository.findAllByActiveGenderTypeTrueOrderByOrderBy();
	}

	@Override
	@Cacheable(cacheNames = "eventPurposesList")
	public List<EventPurposeRef> getAllEventPurposes() {
		return eventPurposeRefRepository.findAllByActiveTrueOrderByOrderBy();
	}

	@Override
	@Cacheable(cacheNames = "cuisinesList")
	public List<Cuisine> getCuisinesList() {
		return cuisinesRepository.findAllBylocTypeIdAndActiveTrue(1);
	}

}
