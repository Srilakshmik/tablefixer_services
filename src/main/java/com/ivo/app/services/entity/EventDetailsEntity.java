package com.ivo.app.services.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "event_details_trans")
public class EventDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "event_uuid")
    private String eventUUID;

    @Column(name = "event_loc_type_id")
    private Integer eventLocationTypeID;

    @Column(name = "event_addr_ln1")
    private String eventAddressLn1;
    @Column(name = "event_addr_ln2")
    private String eventAddressLn2;
    @Column(name = "event_city")
    private String eventCity;
    @Column(name = "event_state")
    private String eventState;
    @Column(name = "event_zip")
    private String eventZip;
    @Column(name = "event_country")
    private String eventCountry;
    @Column(name = "event_lang")
    private Float eventLongitude;
    @Column(name = "event_lat")
    private Float eventLatitude;
    @Column(name = "is_private")
    private Boolean isPrivate;
    @Column(name = "max_guests_allowed")
    private Integer maxGuestsAllowed;
    @Column(name = "event_from_dttm")
    private LocalDateTime eventFromDttm;
    @Column(name = "event_to_dttm")
    private LocalDateTime eventToDttm;
    @Column(name = "max_event_sign_up_dttm")
    private LocalDateTime maxEventSignUpDttm;
    @Column(name = "pay_share_pref_id")
    private Integer paySharePrefId;
    @Column(name = "event_gender_cat_id")
    private Integer eventGenderCatId;
    @Column(name = "event_purpose_id")
    private Integer eventPurposeId;
    @Column(name = "event_age_bracket_start_yrs")
    private Integer eventAgeBracketStartYrs;
    @Column(name = "event_age_bracket_end_yrs")
    private Integer eventAgeBracketEndYrs;
    @Column(name = "pay_share_remarks")
    private String payShareRemarks;
    @Column(name = "is_event_active")
    private Boolean isEventActive;
    @Column(name = "is_event_locked")
    private Boolean isEventLocked;
    @Column(name = "is_event_confirmed")
    private Boolean isEventConfirmed;
    @Column(name = "event_desc")
    private String eventDesc;
    @Column(name = "event_closing_remarks")
    private String eventClosingRemarks;
    @Column(name = "event_short_desc")
    private String eventShortDesc;
    @Column(name = "organizer_uuid")
    private String organizerUUID;
    @Column(name = "is_org_com_pref_email")
    private Boolean orgComPrefEmail;
    @Column(name = "is_org_com_pref_text")
    private Boolean orgComPrefTest;
    @Column(name = "is_org_com_pref_call")
    private Boolean isOrgComPrefCall;
    @Column(name = "is_visitor")
    private Boolean isVisitor;
    @Column(name = "is_event_from_broadcast")
    private Boolean isEventFromBroadcast;
    @Column(name = "broad_cast_id")
    private Long broadCastId;

    @Column(name = "event_location_name")
    private String eventLocationName;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "insrt_dttm")
    private Date insrtDttm;

    @Column(name = "insrt_by")
    private String insrtBy;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updt_dttm")
    private Date updtDttm;

    @Column(name = "updt_by")
    private String updtBy;

    @Column(name = "event_loc_uuid")
    private String eventLocUUID;


    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventUUID() {
        return eventUUID;
    }

    public void setEventUUID(String eventUUID) {
        this.eventUUID = eventUUID;
    }

    public Integer getEventLocationTypeID() {
        return eventLocationTypeID;
    }

    public void setEventLocationTypeID(Integer eventLocationTypeID) {
        this.eventLocationTypeID = eventLocationTypeID;
    }

    public String getEventAddressLn1() {
        return eventAddressLn1;
    }

    public void setEventAddressLn1(String eventAddressLn1) {
        this.eventAddressLn1 = eventAddressLn1;
    }

    public String getEventAddressLn2() {
        return eventAddressLn2;
    }

    public void setEventAddressLn2(String eventAddressLn2) {
        this.eventAddressLn2 = eventAddressLn2;
    }

    public String getEventCity() {
        return eventCity;
    }

    public void setEventCity(String eventCity) {
        this.eventCity = eventCity;
    }

    public String getEventState() {
        return eventState;
    }

    public void setEventState(String eventState) {
        this.eventState = eventState;
    }

    public String getEventZip() {
        return eventZip;
    }

    public void setEventZip(String eventZip) {
        this.eventZip = eventZip;
    }

    public String getEventCountry() {
        return eventCountry;
    }

    public void setEventCountry(String eventCountry) {
        this.eventCountry = eventCountry;
    }

    public Float getEventLongitude() {
        return eventLongitude;
    }

    public void setEventLongitude(Float eventLongitude) {
        this.eventLongitude = eventLongitude;
    }

    public Float getEventLatitude() {
        return eventLatitude;
    }

    public void setEventLatitude(Float eventLatitude) {
        this.eventLatitude = eventLatitude;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Integer getMaxGuestsAllowed() {
        return maxGuestsAllowed;
    }

    public void setMaxGuestsAllowed(Integer maxGuestsAllowed) {
        this.maxGuestsAllowed = maxGuestsAllowed;
    }

    public LocalDateTime getEventFromDttm() {
        return eventFromDttm;
    }

    public void setEventFromDttm(LocalDateTime eventFromDttm) {
        this.eventFromDttm = eventFromDttm;
    }

    public LocalDateTime getEventToDttm() {
        return eventToDttm;
    }

    public void setEventToDttm(LocalDateTime eventToDttm) {
        this.eventToDttm = eventToDttm;
    }

    public LocalDateTime getMaxEventSignUpDttm() {
        return maxEventSignUpDttm;
    }

    public void setMaxEventSignUpDttm(LocalDateTime maxEventSignUpDttm) {
        this.maxEventSignUpDttm = maxEventSignUpDttm;
    }

    public Integer getPaySharePrefId() {
        return paySharePrefId;
    }

    public void setPaySharePrefId(Integer paySharePrefId) {
        this.paySharePrefId = paySharePrefId;
    }

    public Integer getEventGenderCatId() {
        return eventGenderCatId;
    }

    public void setEventGenderCatId(Integer eventGenderCatId) {
        this.eventGenderCatId = eventGenderCatId;
    }

    public Integer getEventPurposeId() {
        return eventPurposeId;
    }

    public void setEventPurposeId(Integer eventPurposeId) {
        this.eventPurposeId = eventPurposeId;
    }

    public Integer getEventAgeBracketStartYrs() {
        return eventAgeBracketStartYrs;
    }

    public void setEventAgeBracketStartYrs(Integer eventAgeBracketStartYrs) {
        this.eventAgeBracketStartYrs = eventAgeBracketStartYrs;
    }

    public Integer getEventAgeBracketEndYrs() {
        return eventAgeBracketEndYrs;
    }

    public void setEventAgeBracketEndYrs(Integer eventAgeBracketEndYrs) {
        this.eventAgeBracketEndYrs = eventAgeBracketEndYrs;
    }

    public String getPayShareRemarks() {
        return payShareRemarks;
    }

    public void setPayShareRemarks(String payShareRemarks) {
        this.payShareRemarks = payShareRemarks;
    }

    public Boolean getEventActive() {
        return isEventActive;
    }

    public void setEventActive(Boolean eventActive) {
        isEventActive = eventActive;
    }

    public Boolean getEventLocked() {
        return isEventLocked;
    }

    public void setEventLocked(Boolean eventLocked) {
        isEventLocked = eventLocked;
    }

    public Boolean getEventConfirmed() {
        return isEventConfirmed;
    }

    public void setEventConfirmed(Boolean eventConfirmed) {
        isEventConfirmed = eventConfirmed;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getEventClosingRemarks() {
        return eventClosingRemarks;
    }

    public void setEventClosingRemarks(String eventClosingRemarks) {
        this.eventClosingRemarks = eventClosingRemarks;
    }

    public String getEventShortDesc() {
        return eventShortDesc;
    }

    public void setEventShortDesc(String eventShortDesc) {
        this.eventShortDesc = eventShortDesc;
    }

    public String getOrganizerUUID() {
        return organizerUUID;
    }

    public void setOrganizerUUID(String organizerUUID) {
        this.organizerUUID = organizerUUID;
    }

    public Boolean getOrgComPrefEmail() {
        return orgComPrefEmail;
    }

    public void setOrgComPrefEmail(Boolean orgComPrefEmail) {
        this.orgComPrefEmail = orgComPrefEmail;
    }

    public Boolean getOrgComPrefTest() {
        return orgComPrefTest;
    }

    public void setOrgComPrefTest(Boolean orgComPrefTest) {
        this.orgComPrefTest = orgComPrefTest;
    }

    public Boolean getOrgComPrefCall() {
        return isOrgComPrefCall;
    }

    public void setOrgComPrefCall(Boolean orgComPrefCall) {
        isOrgComPrefCall = orgComPrefCall;
    }

    public Boolean getVisitor() {
        return isVisitor;
    }

    public void setVisitor(Boolean visitor) {
        isVisitor = visitor;
    }

    public Boolean getEventFromBroadcast() {
        return isEventFromBroadcast;
    }

    public void setEventFromBroadcast(Boolean eventFromBroadcast) {
        isEventFromBroadcast = eventFromBroadcast;
    }

    public Long getBroadCastId() {
        return broadCastId;
    }

    public void setBroadCastId(Long broadCastId) {
        this.broadCastId = broadCastId;
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

    public String getEventLocUUID() {
        return eventLocUUID;
    }

    public void setEventLocUUID(String eventLocUUID) {
        this.eventLocUUID = eventLocUUID;
    }

    public String getUpdtBy() {
        return updtBy;
    }

    public void setUpdtBy(String updtBy) {
        this.updtBy = updtBy;
    }

    public String getEventLocationName() {
        return eventLocationName;
    }

    public void setEventLocationName(String eventLocationName) {
        this.eventLocationName = eventLocationName;
    }
}
