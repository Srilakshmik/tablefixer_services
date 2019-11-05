package com.ivo.app.services.entity;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user_locations_xref")
public class UserLocationsXref {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_addr_id")
    private Long userAddrId;

    @Column(name="user_uuid")
    private String userUuid;

    @Column(name="user_location_name")
    private String userLocationName;

    @Column(name="is_default")
    private Boolean isDefault ;

    @Column(name="is_active")
    private Boolean active;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="insrt_dttm")
    private Date insrtDttm;

    @Column(name="insrt_by")
    private String insrtBy;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updt_dttm")
    private Date updtDttm;

    @Column(name="updt_by")
    private String updtBy;

    @Column(name="longitude")
    private Float longitude;

    @Column(name="latitude")
    private Float latitude;

    @Column(name="address_ln1")
    private String addrLn1;

    @Column(name="address_ln2")
    private String addrLn2;

    @Column(name="zip")
    private String zip;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="country")
    private String country;


    public Long getUserAddrId() {
        return userAddrId;
    }

    public void setUserAddrId(Long userAddrId) {
        this.userAddrId = userAddrId;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getUserLocationName() {
        return userLocationName;
    }

    public void setUserLocationName(String userLocationName) {
        this.userLocationName = userLocationName;
    }



    public Date getInsrtDttm() {
        return insrtDttm;
    }

    public void setInsrtDttm(Date insrtDttm) {
        this.insrtDttm = insrtDttm;
    }

    public String getInsrtBy() {
        return insrtBy;
    }

    public void setInsrtBy(String insrtBy) {
        this.insrtBy = insrtBy;
    }

    public Date getUpdtDttm() {
        return updtDttm;
    }

    public void setUpdtDttm(Date updtDttm) {
        this.updtDttm = updtDttm;
    }

    public String getUpdtBy() {
        return updtBy;
    }

    public void setUpdtBy(String updtBy) {
        this.updtBy = updtBy;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public String getAddrLn1() {
        return addrLn1;
    }

    public void setAddrLn1(String addrLn1) {
        this.addrLn1 = addrLn1;
    }

    public String getAddrLn2() {
        return addrLn2;
    }

    public void setAddrLn2(String addrLn2) {
        this.addrLn2 = addrLn2;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }



}
