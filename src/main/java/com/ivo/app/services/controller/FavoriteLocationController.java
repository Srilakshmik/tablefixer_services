package com.ivo.app.services.controller;

import com.ivo.app.services.domain.LocationSearchResponse;
import com.ivo.app.services.entity.UserFavoriteLocationsXref;
import com.ivo.app.services.request.FavoriteLocationRequest;
import com.ivo.app.services.service.FavoriteLocationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value="/location")
public class FavoriteLocationController {
	private static final Logger logger = LogManager.getLogger(FavoriteLocationController.class);
	@Autowired
    private FavoriteLocationService favoriteLocationService;
	
	@PostMapping(value="/v1/favorite/{userUUID}/create/{locationUUID}")
	public ResponseEntity<UserFavoriteLocationsXref> createUserFavoriteLocation(@PathVariable(value="userUUID") String userUUID, @PathVariable(value="locationUUID") String locationUUID) throws URISyntaxException{

    		logger.info("userUUID:"+userUUID+",locationUUID:"+locationUUID);
        UserFavoriteLocationsXref userFavoriteLocationsXref = favoriteLocationService.createUserFavoriteLocation(userUUID, locationUUID);
        return ResponseEntity.created(new URI("/v1/favorite/userUUID/create/locationUUID")).body(userFavoriteLocationsXref);
	}
	
	@DeleteMapping(value="/v1/favorite/{userUUID}/delete/{locationUUID}")
    public ResponseEntity<Object> deleteUserFavoriteLocation(@PathVariable(value = "userUUID") String userUUID, @PathVariable(value = "locationUUID") String locationUUID) {

		logger.info("userUUID:"+userUUID+",locationUUID:"+locationUUID);

        long numOfRowsDeleted = favoriteLocationService.deleteUserFavoriteLocation(userUUID, locationUUID);
		logger.info(" deleted rows #"+numOfRowsDeleted);
		return ResponseEntity.noContent().build();
	}

	
	@PostMapping(value="v1/favorite/list/{userUUID}")
    public ResponseEntity<List<LocationSearchResponse>> getUserFavoriteLocation(@PathVariable(value = "userUUID") String userUUID, @RequestBody @Valid FavoriteLocationRequest favoriteLocationRequest, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {

		logger.info("Getting list of favorite restaurants by userUUID:"+userUUID);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
		List<LocationSearchResponse> userFavoriteLocations=favoriteLocationService.getUserFavoriteLocation(userUUID,favoriteLocationRequest, pageable);
		logger.info(" User Favorite locations list size #"+userFavoriteLocations.size());
		return ResponseEntity.ok().body(userFavoriteLocations);
	}
}
