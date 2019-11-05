package com.ivo.app.services.serviceimpl;

import com.ivo.app.services.dao.LocationSearchDao;
import com.ivo.app.services.domain.AddressSearchResponse;
import com.ivo.app.services.domain.LocationSearchRequest;
import com.ivo.app.services.domain.LocationSearchResponse;
import com.ivo.app.services.entity.UserInfoRef;
import com.ivo.app.services.repository.UserInfoRefRepository;
import com.ivo.app.services.service.LocationSearchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LocationSearchServiceImpl implements LocationSearchService {
	private static final Logger logger = LogManager.getLogger(LocationSearchServiceImpl.class);

	@Autowired
	private LocationSearchDao locationSearchDao;

	@Autowired
	private UserInfoRefRepository userInfoRefRepository;

	@Override
	public List<LocationSearchResponse> searchLocations(String userUUID,LocationSearchRequest locationSearchRequest,
			Pageable pageable) {
		UserInfoRef userInfoRef =userInfoRefRepository.findByuserUUID(userUUID);
		if(userInfoRef==null){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid 'userUUID'");
		}
		return locationSearchDao.searchLocations(userUUID, locationSearchRequest, pageable);
	}
  
	@Override
	public List<AddressSearchResponse> searchAddress(String searchKey, String userUUID, Pageable pageable) {
		UserInfoRef userInfoRef =userInfoRefRepository.findByuserUUID(userUUID);
		if(userInfoRef==null){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid 'userUUID'");
		}
		return locationSearchDao.searchAddress(searchKey, userUUID, pageable);
	}

}
