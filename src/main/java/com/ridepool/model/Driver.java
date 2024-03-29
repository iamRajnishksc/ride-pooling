package com.ridepool.model;

public class Driver extends Profile {

    private String drivingLicence;

    public Driver(Integer id, String name, String email, Long phoneNo, String drivingLicence) {
        super(id, name, email, phoneNo);
        this.drivingLicence = drivingLicence;
    }

    public String getDrivingLicence() {
        return drivingLicence;
    }

    public void setDrivingLicence(String drivingLicence) {
        this.drivingLicence = drivingLicence;
    }
}
