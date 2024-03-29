package com.foodkart.service;

import com.foodkart.model.Location;
import com.foodkart.model.Ride;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface RideService {

    public List<Ride> getRides(Location src, Location des, LocalDateTime startsAt);
    public Ride getRide(Integer rideID);
    public Ride addRide(Ride ride);


}
