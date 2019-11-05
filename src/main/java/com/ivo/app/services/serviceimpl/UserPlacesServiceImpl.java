package com.ivo.app.services.serviceimpl;

import com.ivo.app.services.dao.UserPlacesDao;
import com.ivo.app.services.domain.UserPlacesResponse;
import com.ivo.app.services.entity.UserLocationsXref;
import com.ivo.app.services.repository.UserLocationsXrefReepository;
import com.ivo.app.services.request.UserLocationRequest;
import com.ivo.app.services.service.UserPlacesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserPlacesServiceImpl implements UserPlacesService {

    private static final Logger logger = LogManager.getLogger(UserPlacesServiceImpl.class);
  
    @Autowired
    private UserPlacesDao userPlacesDao;


    @Autowired
    private UserLocationsXrefReepository userLocationsXrefReepository;

    @Override
    public List<UserPlacesResponse> getUserPlaces(String userUUID) {
        return userLocationsXrefReepository.findByUserUuidAndActiveTrue(userUUID);
    }

    @Override
    public UserLocationsXref createLabelForMyPlaces(UserLocationRequest userLocation, String userUUID,
                                                    Long userAddrId) {

        if (checkLocationNameUniquenessByUser(userUUID, userLocation.getUserLocationName()) == false) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is already an existing location name. Please use different label name");
        }

        return userPlacesDao.createLableForMyPlaces(userLocation, userUUID, userAddrId);
    }
   @Override
   public int updateLabelForMyPlaces(UserLocationRequest userLocation, Long userAdrId, String userUUID) {

       return userPlacesDao.updateLableForMyPlaces(userLocation,userAdrId);
   }

    @Override
    public long deleteLabelForMyPlaces(String userUUID, Long useerAdrId) {
        return userLocationsXrefReepository.deleteByuserUuidAndUserAddrId(userUUID,  useerAdrId);
    }

    private Boolean checkLocationNameUniquenessByUser(String userUUID, String userLocationName) {
        int count =userLocationsXrefReepository.countByUserUuidAndUserLocationNameEqualsIgnoreCase(userUUID,userLocationName);
        System.out.println("Existing user places count ="+count);

        if(count==0){
            return true;
        }else return false;
    }
}
