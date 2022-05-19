package com.mgs_quiz.mgsquizfree.model;

import androidx.annotation.Keep;

@Keep
public class User {

    private String email;
    private String username;
    private String gender;
    private String birthday;
    private String country;
    private String countryCode;
    private String registerdate;
    private int sdk;
    private String dres;

    public User() {
    }

    public User(String email, String username, String gender, String birthday, String country,
                String countryCode, String registerdate, int sdk, String dres) {
        this.email = email;
        this.username = username;
        this.gender = gender;
        this.birthday = birthday;
        this.country = country;
        this.countryCode = countryCode;
        this.registerdate = registerdate;
        this.sdk = sdk;
        this.dres = dres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(String registerdate) {
        this.registerdate = registerdate;
    }

    public int getSdk() {
        return sdk;
    }

    public void setSdk(int sdk) {
        this.sdk = sdk;
    }

    public String getDres() {
        return dres;
    }

    public void setDres(String dres) {
        this.dres = dres;
    }

}
