package com.maniraghu.flashchatnewfirebase.ui.profile;

public class UserInformation {

    private String username;
    private String companyname;
    private String region;

    public UserInformation() {
    }

    public UserInformation(String username, String companyname, String region) {
        this.username = username;
        this.companyname = companyname;
        this.region = region;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
