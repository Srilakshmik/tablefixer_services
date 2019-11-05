package com.ivo.app.services.daoimpl;

import com.ivo.app.services.dao.UserPlacesDao;
import com.ivo.app.services.entity.UserLocationsXref;
import com.ivo.app.services.repository.UserLocationsXrefReepository;
import com.ivo.app.services.request.UserLocationRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class UserPlacesDaoImpl implements UserPlacesDao {
    private static final Logger logger = LogManager.getLogger(UserPlacesDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private UserLocationsXrefReepository userLocationsXrefReepository;

    @Override
    public UserLocationsXref createLableForMyPlaces(UserLocationRequest userLocation, String userUUID, Long userAddrId) {
        UserLocationsXref userLocationsXref = new UserLocationsXref();

        if (!StringUtils.isEmpty(userAddrId)) {
            userLocationsXref.setUserAddrId(userAddrId);
        }
        userLocationsXref.setUserUuid(userUUID);
        userLocationsXref.setDefault(userLocation.getDefaultLocation());
        userLocationsXref.setUserLocationName(userLocation.getUserLocationName());
        if (!StringUtils.isEmpty(userLocation.getLongitude())) {
            userLocationsXref.setLongitude(Float.parseFloat(userLocation.getLongitude()));
        }
        if (!StringUtils.isEmpty(userLocation.getLatitude())) {
            userLocationsXref.setLatitude(Float.parseFloat(userLocation.getLatitude()));
        }
        userLocationsXref.setAddrLn1(userLocation.getAddrLn1());
        userLocationsXref.setAddrLn2(userLocation.getAddrLn2());
        userLocationsXref.setCity(userLocation.getCity());
        userLocationsXref.setState(userLocation.getState());
        userLocationsXref.setZip(userLocation.getZip());
        userLocationsXref.setCountry(userLocation.getCountry());
        userLocationsXref.setInsrtBy(userUUID);
        userLocationsXref.setUpdtBy(userUUID);
        userLocationsXref.setActive(true);
        return userLocationsXrefReepository.save(userLocationsXref);
    }
   @Override
    public int updateLableForMyPlaces(UserLocationRequest userLocation,Long userAdrId) {

       return userLocationsXrefReepository.updateUserLocation(userAdrId,userLocation.getUserLocationName());
    }
    @Override
    public long deleteLableForMyPlaces(String userUuid,Long userAdrId) {

        return userLocationsXrefReepository.deleteByuserUuidAndUserAddrId(userUuid,userAdrId);
    }


}
