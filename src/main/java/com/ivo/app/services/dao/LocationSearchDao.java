package com.ivo.app.services.dao;

import com.ivo.app.services.domain.AddressSearchResponse;
import com.ivo.app.services.domain.LocationDetails;
import com.ivo.app.services.domain.LocationSearchRequest;
import com.ivo.app.services.domain.LocationSearchResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LocationSearchDao {

	List<LocationSearchResponse> searchLocations(String userUUID, LocationSearchRequest locationSearchRequest, Pageable pageable);

  List<AddressSearchResponse> searchAddress(String searchKey, String userUUID, Pageable pageable);

	LocationDetails getLocationDetailsByLocationUUID(String locationUUID);

}
