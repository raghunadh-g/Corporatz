package com.maniraghu.flashchatnewfirebase.ui.dashboard.JobSecurity;

public class Job {
    public String userId,name,area,qualification,hospital,timings,mobile;

    public Job() {
    }

    public Job(String userId, String name, String area, String qualification, String hospital, String timings, String mobile) {
        this.userId = userId;
        this.name = name;
        this.area = area;
        this.qualification = qualification;
        this.hospital = hospital;
        this.timings = timings;
        this.mobile = mobile;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}

