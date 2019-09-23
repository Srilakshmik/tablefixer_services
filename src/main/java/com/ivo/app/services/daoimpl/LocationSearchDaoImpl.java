package com.ivo.app.services.daoimpl;

import com.ivo.app.services.dao.LocationSearchDao;
import com.ivo.app.services.domain.AddressSearchResponse;
import com.ivo.app.services.domain.LocationSearchRequest;
import com.ivo.app.services.domain.LocationSearchResponse;
import com.ivo.app.services.util.LocationSearchConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LocationSearchDaoImpl implements LocationSearchDao {

	@Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	@Override
    public List<LocationSearchResponse> searchLocations(String userUUID, LocationSearchRequest locationSearchRequest, Pageable pageable) {
        Map<String, Object> params = new HashMap<>();
		params.put("UUID", userUUID);
        params.put("locationType", locationSearchRequest.getUserBookMarkLocationType());
		params.put("radius", new Float(locationSearchRequest.getSearchRadiusMiles()));
            params.put("lng", Double.parseDouble(locationSearchRequest.getLongitude()));
            params.put("lat", Double.parseDouble(locationSearchRequest.getLatitude()));
        params.put("cuisineType", "%" + locationSearchRequest.getCuisineType() + "%");
        params.put("locationName", "%" + locationSearchRequest.getLocationNameSearchString() + "%");

        String queryString = null;
        if (!StringUtils.isEmpty(locationSearchRequest.getCuisineType()) && locationSearchRequest.getCuisineType().trim().length() > 0) {
            queryString = LocationSearchConstants.QUERY_SEARCH_BY_CUISINE_TYPES;
        } else {
            queryString = LocationSearchConstants.QUERY_SEARCH_LOCATIONS_BY_GPS_COORDINATES;
        }
        if (!StringUtils.isEmpty(locationSearchRequest.getLocationNameSearchString()) && locationSearchRequest.getLocationNameSearchString().trim().length() > 0) {
            queryString += LocationSearchConstants.QUERY_ADD_SEARCH_BY_RESTAURANT_NAME;
        }
        queryString += LocationSearchConstants.QUERY_ADD_ORDER_BY_DISTANCE_LOCATION_TABLE_REFERENCE;

        System.out.println(queryString);
        return namedParameterJdbcTemplate.query(queryString +
                    " limit " + pageable.getPageSize() + " offset " + (pageable.getPageNumber() - 1) * pageable.getPageSize(), params, new BeanPropertyRowMapper<>(LocationSearchResponse.class));

	}
	
	@Override
    public List<LocationSearchResponse> getUserFavoriteLocation(String userUUID, LocationSearchRequest locationSearchRequest, Pageable pageable) {
        Map<String, Object> params = new HashMap<>();
		
		System.out.println(locationSearchRequest.getLongitude());
		System.out.println(userUUID);
		System.out.println(locationSearchRequest.getLatitude());
		System.out.println(pageable.getPageSize());
		System.out.println((pageable.getPageNumber()-1)*pageable.getPageSize());
        params.put("userUuid", userUUID);
		params.put("radius", new Float(locationSearchRequest.getSearchRadiusMiles()));
        params.put("lng", Double.parseDouble(locationSearchRequest.getLongitude()));
        params.put("lat", Double.parseDouble(locationSearchRequest.getLatitude()));
        return namedParameterJdbcTemplate.query(LocationSearchConstants.QUERY_USER_FAVORITE_LOCATIONS_BY_GPS_COORDINATES + " limit " + pageable.getPageSize() + " offset " + (pageable.getPageNumber() - 1) * pageable.getPageSize(), params, new BeanPropertyRowMapper<>(LocationSearchResponse.class));

	}

    @Override
    public List<AddressSearchResponse> searchAddress(String searchKey, String userUUID, Pageable pageable) {
        Map<String, String> params = new HashMap<>();
        params.put("searchKey", "%" + searchKey + "%");
        return namedParameterJdbcTemplate.query(LocationSearchConstants.QUERY_GENERIC_ADDRESS_SEARCH + " limit " + pageable.getPageSize() + " offset " + (pageable.getPageNumber() - 1) * pageable.getPageSize(), params, new BeanPropertyRowMapper<>(AddressSearchResponse.class));
    }

}
