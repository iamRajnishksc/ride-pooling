package com.ridepool.service;

import com.ridepool.model.Location;
import com.ridepool.model.Ride;

import java.time.LocalDateTime;
import java.util.List;

public interface RideService {

    public List<Ride> getRides(Location src, Location des, LocalDateTime startsAt);
    public Ride getRide(Integer rideID);
    public Ride addRide(Ride ride);


}
