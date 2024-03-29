package com.foodkart.service;

import com.foodkart.model.Location;
import com.foodkart.model.Ride;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RideServiceImpl implements RideService{
    private static RideServiceImpl rideService;
    private Map<Integer,Ride> rides;

    private RideServiceImpl(Map<Integer, Ride> rides) {
        this.rides = rides;
    }

    public static RideServiceImpl getInstance(){
        if(rideService==null){
            init();
        }

        return rideService;

    }

    private synchronized static void init() {
        if(rideService==null)
    rideService= new RideServiceImpl(new HashMap<>());
    }

    @Override
    public List<Ride> getRides(Location src, Location des, LocalDateTime startsAt) {
        return rides.values().stream().filter(ride -> ride.getSrc().city.equals(src.city)&ride.getDst().city.equals(des.city))
                .filter(ride -> ride.getStartsAt().toLocalDate().equals(startsAt.toLocalDate()))
                .filter(ride -> ride.getStartsAt().toEpochSecond(ZoneOffset.MAX)>=startsAt.toEpochSecond(ZoneOffset.MAX)).collect(Collectors.toList());
    }

    @Override
    public Ride getRide(Integer rideID) {
        return this.rides.getOrDefault(rideID,null);
    }

    @Override
    public Ride addRide(Ride ride) {
        return this.rides.put(ride.getId(),ride);
    }
}
