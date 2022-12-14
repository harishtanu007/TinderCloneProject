package com.harish.tinder.model;

import java.util.List;

public class FirebaseDbUser {
    public String name;
    public String email;
    public int dob;
    public String online;
    public String profileImageUrl;
    public String gender;
    public String profileImageUrlCompressed;
    public String uid;
    public String schoolName;
    public List<Interest> interests;
    public String aboutMe;
    public RequestLocation location;
    public boolean termsAgreed;
    public String status;
    public String deviceToken;

    public FirebaseDbUser() {

    }

    public FirebaseDbUser(String name, String email, int dob, String online, String profileImageUrl, String gender, String profileImageUrlCompressed, String uid) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.online = online;
        this.profileImageUrl = profileImageUrl;
        this.gender = gender;
        this.profileImageUrlCompressed = profileImageUrlCompressed;
        this.uid = uid;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public List<Interest> getInterests() {
        return interests;
    }

    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public RequestLocation getLocation() {
        return location;
    }

    public void setLocation(RequestLocation location) {
        this.location = location;
    }


    public boolean isTermsAgreed() {
        return termsAgreed;
    }

    public void setTermsAgreed(boolean termsAgreed) {
        this.termsAgreed = termsAgreed;
    }


    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDob() {
        return dob;
    }

    public void setDob(int dob) {
        this.dob = dob;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfileImageUrlCompressed() {
        return profileImageUrlCompressed;
    }

    public void setProfileImageUrlCompressed(String profileImageUrlCompressed) {
        this.profileImageUrlCompressed = profileImageUrlCompressed;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
