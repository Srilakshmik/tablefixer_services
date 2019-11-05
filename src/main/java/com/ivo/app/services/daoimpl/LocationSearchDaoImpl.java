package com.ivo.app.services.daoimpl;

import com.ivo.app.services.dao.LocationSearchDao;
import com.ivo.app.services.domain.AddressSearchResponse;
import com.ivo.app.services.domain.LocationDetails;
import com.ivo.app.services.domain.LocationSearchRequest;
import com.ivo.app.services.domain.LocationSearchResponse;
import com.ivo.app.services.util.LocationSearchConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LocationSearchDaoImpl implements LocationSearchDao {
    private static final Logger logger = LogManager.getLogger(LocationSearchDaoImpl.class);

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
    public List<LocationSearchResponse> searchLocations(String userUUID, LocationSearchRequest locationSearchRequest, Pageable pageable) {
        Map<String, Object> params = new HashMap<>();
        params.put("UUID", userUUID);
//        params.put("locationType", locationSearchRequest.getUserBookMarkLocationType());
        params.put("radius", new Float(locationSearchRequest.getSearchRadiusMiles()));
        params.put("lng", Double.parseDouble(locationSearchRequest.getFromLongitude()));
        params.put("lat", Double.parseDouble(locationSearchRequest.getFromLatitude()));
        params.put("cuisineType", "%" + locationSearchRequest.getCuisineType() + "%");
        params.put("locationName", "%" + locationSearchRequest.getLocationNameSearchString() + "%");

        String queryString ;
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
    public List<AddressSearchResponse> searchAddress(String searchKey, String userUUID, Pageable pageable) {
        Map<String, String> params = new HashMap<>();
        params.put("searchKey", "%" + searchKey + "%");
        return namedParameterJdbcTemplate.query(LocationSearchConstants.QUERY_GENERIC_ADDRESS_SEARCH + " limit " + pageable.getPageSize() + " offset " + (pageable.getPageNumber() - 1) * pageable.getPageSize(), params, new BeanPropertyRowMapper<>(AddressSearchResponse.class));
    }

    @Override
    public LocationDetails getLocationDetailsByLocationUUID(String locationUUID) {
        Map<String, String> params = new HashMap<>();
        params.put("locationUUID", locationUUID);
        List<LocationDetails> locationDetails = namedParameterJdbcTemplate.query(" select loc_uuid locationUUID ,loc_type_id locationTypeId,loc_name locName,email,website," +
                " contact_num_1 contactNum1,address_ln1 addrLn1 ,address_ln2 addrLn2,city,state,zip_code zip,lang longitude, lat latitude," +
                " country  from location_info_ref where " +
                " loc_uuid=:locationUUID" +
                " and is_active=true", params, new BeanPropertyRowMapper<>(LocationDetails.class));
        if (locationDetails.size() > 0) {
            return locationDetails.get(0);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid 'locationUUID'");
        }
    }

}
