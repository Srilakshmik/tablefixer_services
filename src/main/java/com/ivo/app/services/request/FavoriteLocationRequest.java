package com.ivo.app.services.request;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class FavoriteLocationRequest implements Serializable{

	private static final long serialVersionUID = -8409039374798512885L;


    //    @Digits(message = "'longitude' should be a decimal value", fraction = 10, integer = 10)
    private String fromLongitude;

    //    @Digits(message = "'latitude' should be a decimal value", fraction = 10, integer = 10)
    private String fromLatitude;

    private String fromUserBookMarkLocationType; //Home, Office .. etc

    @NotNull
    @NotEmpty
    @Min(1)
    @Max(500)
    private String searchRadiusMiles;


    public String getFromLongitude() {
        return fromLongitude;
    }

    public void setFromLongitude(String fromLongitude) {
        this.fromLongitude = fromLongitude;
    }

    public String getFromLatitude() {
        return fromLatitude;
    }

    public void setFromLatitude(String fromLatitude) {
        this.fromLatitude = fromLatitude;
    }

    public String getSearchRadiusMiles() {
        return searchRadiusMiles;
    }

    public void setSearchRadiusMiles(String searchRadiusMiles) {
        this.searchRadiusMiles = searchRadiusMiles;
    }

    public String getFromUserBookMarkLocationType() {
        return fromUserBookMarkLocationType;
    }

    public void setFromUserBookMarkLocationType(String fromUserBookMarkLocationType) {
        this.fromUserBookMarkLocationType = fromUserBookMarkLocationType;
    }
}
