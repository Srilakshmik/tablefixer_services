package com.ivo.app.services.serviceimpl;

import com.ivo.app.services.dao.FavoriteLocationDao;
import com.ivo.app.services.domain.LocationSearchResponse;
import com.ivo.app.services.entity.UserFavoriteLocationsXref;
import com.ivo.app.services.repository.UserFavoriteLocationsXrefRepository;
import com.ivo.app.services.request.FavoriteLocationRequest;
import com.ivo.app.services.service.FavoriteLocationService;
import com.ivo.app.services.util.LocationSearchConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class FavoriteLocationServiceImpl implements FavoriteLocationService {
	private static final Logger logger = LogManager.getLogger(FavoriteLocationServiceImpl.class);
	@Autowired
	private UserFavoriteLocationsXrefRepository userFavoriteLocationsXrefRepository;

	@Autowired
	private FavoriteLocationDao favoriteLocationDao;

	@Override
	public UserFavoriteLocationsXref createUserFavoriteLocation(String userUUID, String locationUUID) {

		UserFavoriteLocationsXref userFavoriteLocationsXref = new UserFavoriteLocationsXref();

		userFavoriteLocationsXref.setUserUuid(userUUID);
		userFavoriteLocationsXref.setLocationUuid(locationUUID);
		userFavoriteLocationsXref.setInsrtBy(userUUID);
		userFavoriteLocationsXref.setUpdtBy(userUUID);

		return userFavoriteLocationsXrefRepository.save(userFavoriteLocationsXref);
	}

	@Override
	public long deleteUserFavoriteLocation(String userUUID, String locationUUID) {
		return userFavoriteLocationsXrefRepository.deleteByUserUuidAndLocationUuid(userUUID,  locationUUID);
	}

	@Override
	public List<LocationSearchResponse> getUserFavoriteLocation(String userUUID, FavoriteLocationRequest favoriteLocationRequest, Pageable pageable) {

        if (!StringUtils.isEmpty(favoriteLocationRequest.getFromLongitude()) && !StringUtils.isEmpty(favoriteLocationRequest.getFromLatitude())) {
			return favoriteLocationDao.getUserFavoriteLocation(userUUID,  favoriteLocationRequest, LocationSearchConstants.GEO_COORDINATES,pageable);
        } else if (!StringUtils.isEmpty(favoriteLocationRequest.getFromUserBookMarkLocationType())) {
			return favoriteLocationDao.getUserFavoriteLocation(userUUID,  favoriteLocationRequest, LocationSearchConstants.USER_LOCATIONS,pageable);
		}else {
            return favoriteLocationDao.getUserFavoriteLocation(userUUID,  favoriteLocationRequest, null,pageable);
		}
	}


}
