package com.ivo.app.services.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user_info_ref")
public class UserInfoRef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @Column(name="user_uuid")
    private String userUUID;

    @Column(name="user_name")
    private String userName;

    @Column(name="sign_up_type")
    private String signUpType;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="middle_name")
    private String middleName;

    @Column(name="dob")
    private Date dob;

    @Column(name="email")
    private String email;

    @Column(name="contact_num")
    private String contactNum;

    @Column(name="alter_contact")
    private String alterContact;

    @Column(name="is_active")
    private Boolean isActive;

    @Column(name="user_sign_up_date")
    private Date userSignUpDate;

    @Column(name="remarks")
    private String remarks;

    @Column(name="twitter_link")
    private String twitterLink;

    @Column(name="linkedin_link")
    private String linkedinLink;

    @Column(name="facebook_link")
    private String facebookLink;

    @Column(name="current_rating")
    private Float currentRating;

    @Column(name="insrt_dttm")
    private Date insrt_dttm;

    @Column(name="updt_dttm")
    private Date updt_dttm;

    @Column(name="max_guests_allowed_per_event")
    private Integer maxGuestsAallowedPerEvent;

    @Column(name="is_event_com_pref_email")
    private Boolean isEventComPrefEmail;

    @Column(name="is_event_com_pref_text")
    private Boolean isEventComPrefText;

    @Column(name="is_event_com_pref_call")
    private Boolean isEventComPrefCall;

    @Column(name="gender_id")
    private Integer genderId;

    @Column(name="gender_cd")
    private String genderCd;

    @Column(name="daily_events_limit")
    private Integer dailyEventsLimit;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSignUpType() {
        return signUpType;
    }

    public void setSignUpType(String signUpType) {
        this.signUpType = signUpType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getAlterContact() {
        return alterContact;
    }

    public void setAlterContact(String alterContact) {
        this.alterContact = alterContact;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Date getUserSignUpDate() {
        return userSignUpDate;
    }

    public void setUserSignUpDate(Date userSignUpDate) {
        this.userSignUpDate = userSignUpDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTwitterLink() {
        return twitterLink;
    }

    public void setTwitterLink(String twitterLink) {
        this.twitterLink = twitterLink;
    }

    public String getLinkedinLink() {
        return linkedinLink;
    }

    public void setLinkedinLink(String linkedinLink) {
        this.linkedinLink = linkedinLink;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public Float getCurrentRating() {
        return currentRating;
    }

    public void setCurrentRating(Float currentRating) {
        this.currentRating = currentRating;
    }

    public Date getInsrt_dttm() {
        return insrt_dttm;
    }

    public void setInsrt_dttm(Date insrt_dttm) {
        this.insrt_dttm = insrt_dttm;
    }

    public Date getUpdt_dttm() {
        return updt_dttm;
    }

    public void setUpdt_dttm(Date updt_dttm) {
        this.updt_dttm = updt_dttm;
    }

    public Integer getMaxGuestsAallowedPerEvent() {
        return maxGuestsAallowedPerEvent;
    }

    public void setMaxGuestsAallowedPerEvent(Integer maxGuestsAallowedPerEvent) {
        this.maxGuestsAallowedPerEvent = maxGuestsAallowedPerEvent;
    }

    public Boolean getEventComPrefEmail() {
        return isEventComPrefEmail;
    }

    public void setEventComPrefEmail(Boolean eventComPrefEmail) {
        isEventComPrefEmail = eventComPrefEmail;
    }

    public Boolean getEventComPrefText() {
        return isEventComPrefText;
    }

    public void setEventComPrefText(Boolean eventComPrefText) {
        isEventComPrefText = eventComPrefText;
    }

    public Boolean getEventComPrefCall() {
        return isEventComPrefCall;
    }

    public void setEventComPrefCall(Boolean eventComPrefCall) {
        isEventComPrefCall = eventComPrefCall;
    }

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    public String getGenderCd() {
        return genderCd;
    }

    public void setGenderCd(String genderCd) {
        this.genderCd = genderCd;
    }

    public Integer getDailyEventsLimit() {
        return dailyEventsLimit;
    }

    public void setDailyEventsLimit(Integer dailyEventsLimit) {
        this.dailyEventsLimit = dailyEventsLimit;
    }


}
