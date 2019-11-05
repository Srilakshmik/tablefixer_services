package com.ivo.app.services.daoimpl;

import com.ivo.app.services.dao.FavoriteLocationDao;
import com.ivo.app.services.domain.LocationSearchResponse;
import com.ivo.app.services.request.FavoriteLocationRequest;
import com.ivo.app.services.util.LocationSearchConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FavoriteLocationDaoImpl implements FavoriteLocationDao {
    private static final Logger logger = LogManager.getLogger(FavoriteLocationDaoImpl.class);
	@Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public List<LocationSearchResponse> getUserFavoriteLocation(String userUUID, FavoriteLocationRequest favoriteLocationRequest,
                                                                String searchStrategy, Pageable pageable) {
        Map<String, Object> params = new HashMap<>();

        System.out.println(favoriteLocationRequest.getFromLongitude());
        System.out.println(userUUID);
        System.out.println(favoriteLocationRequest.getFromLongitude());
        System.out.println(pageable.getPageSize());
        System.out.println((pageable.getPageNumber()-1)*pageable.getPageSize());
        params.put("userUuid", userUUID);
        params.put("radius", new Float(favoriteLocationRequest.getSearchRadiusMiles()));
        if(LocationSearchConstants.GEO_COORDINATES.equals(searchStrategy)){
            params.put("lng", Double.parseDouble(favoriteLocationRequest.getFromLongitude()));
            params.put("lat", Double.parseDouble(favoriteLocationRequest.getFromLatitude()));
            return namedParameterJdbcTemplate.query(LocationSearchConstants.QUERY_USER_FAVORITE_LOCATIONS_BY_GPS_COORDINATES + " limit " + pageable.getPageSize() + " offset " + (pageable.getPageNumber() - 1) * pageable.getPageSize(), params, new BeanPropertyRowMapper<>(LocationSearchResponse.class));
        }else if(LocationSearchConstants.USER_LOCATIONS.equals(searchStrategy)){
            params.put("userLocationType", favoriteLocationRequest.getFromUserBookMarkLocationType().toUpperCase());
            return namedParameterJdbcTemplate.query(LocationSearchConstants.QUERY_USER_FAVORITE_LOCATIONS_BY_BOOK_MARKED_COORDINATES + " limit " + pageable.getPageSize() + " offset " + (pageable.getPageNumber() - 1) * pageable.getPageSize(), params, new BeanPropertyRowMapper<>(LocationSearchResponse.class));
        }else {
            return namedParameterJdbcTemplate.query(LocationSearchConstants.QUERY_USER_FAVORITE_LOCATIONS_BY_DEFAULT + " limit " + pageable.getPageSize() + " offset " + (pageable.getPageNumber() - 1) * pageable.getPageSize(), params, new BeanPropertyRowMapper<>(LocationSearchResponse.class));
        }

    }


}
