package com.ivo.app.services.request;

import com.ivo.app.services.domain.Address;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserLocationRequest extends  Address implements Serializable  {

    @NotNull
    @NotEmpty
    @ApiModelProperty(required = true)
    private String userLocationName;
    private Boolean active;
    private Boolean defaultLocation;

    @NotNull
    @NotEmpty
    @Digits(message = "'longitude' should be a decimal value", fraction = 10, integer = 10)
    @ApiModelProperty(required = true)
    private String longitude;

    @NotNull
    @NotEmpty
    @Digits(message = "'latitude' should be a decimal value", fraction = 10, integer = 10)
    @ApiModelProperty(required = true)
    private String latitude;


    public String getUserLocationName() {
        return userLocationName;
    }

    public void setUserLocationName(String userLocationName) {
        this.userLocationName = userLocationName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getDefaultLocation() {
        return defaultLocation;
    }

    public void setDefaultLocation(Boolean defaultLocation) {
        this.defaultLocation = defaultLocation;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }


}
