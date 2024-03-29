package com.foodkart.model;

import com.foodkart.enums.RideStatus;

import java.time.LocalDateTime;
import java.util.List;

public class Ride {

    final Integer id;
    final Location src;
    final Location dst;
    private List<Integer> riderList;
    private LocalDateTime startsAt;
    final Integer driverId;

    private RideStatus rideStatus;

    private Integer noOfSeat;

    public Ride(Integer id, Location src, Location dst, List<Integer> riderList, LocalDateTime startsAt, Integer driverId, RideStatus rideStatus, Integer noOfSeat) {
        this.id = id;
        this.src = src;
        this.dst = dst;
        this.riderList = riderList;
        this.startsAt = startsAt;
        this.driverId = driverId;
        this.rideStatus = rideStatus;
        this.noOfSeat = noOfSeat;
    }

    public Integer getId() {
        return id;
    }

    public Location getSrc() {
        return src;
    }

    public Location getDst() {
        return dst;
    }

    public List<Integer> getRiderList() {
        return riderList;
    }

    public void setRiderList(List<Integer> riderList) {
        this.riderList = riderList;
    }

    public LocalDateTime getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(LocalDateTime startsAt) {
        this.startsAt = startsAt;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }

    public Integer getNoOfSeat() {
        return noOfSeat;
    }

    public void setNoOfSeat(Integer noOfSeat) {
        this.noOfSeat = noOfSeat;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public synchronized void addRider(Integer userId){
        if(this.riderList.size()<noOfSeat){
            riderList.add(userId);
        }
        else throw new RuntimeException("capacity full");
    }
}
