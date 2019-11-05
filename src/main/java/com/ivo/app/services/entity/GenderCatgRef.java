package com.ivo.app.services.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gender_catg_ref")
public class GenderCatgRef {


    @Id
    @Column(name = "gender_id")
    private Integer genderId;

    @Column(name = "gender_name")
    private String genderName;

    @Column(name = "gender_display_name")
    private String eventDisplayName;

    @Column(name = "is_active_gender_type")
    private boolean activeGenderType;

    @Column(name = "is_active_event_type")
    private boolean activeEventType;

    @Column(name = "order_by")
    private Integer orderBy;

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public String getEventDisplayName() {
        return eventDisplayName;
    }

    public void setEventDisplayName(String eventDisplayName) {
        this.eventDisplayName = eventDisplayName;
    }

    public boolean isActiveGenderType() {
        return activeGenderType;
    }

    public void setActiveGenderType(boolean activeGenderType) {
        this.activeGenderType = activeGenderType;
    }

    public boolean isActiveEventType() {
        return activeEventType;
    }

    public void setActiveEventType(boolean activeEventType) {
        this.activeEventType = activeEventType;
    }

    public Integer isOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }
}
