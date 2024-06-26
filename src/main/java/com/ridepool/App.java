package com.ridepool;

import com.ridepool.enums.RideStatus;
import com.ridepool.enums.VehicleType;
import com.ridepool.model.*;
import com.ridepool.service.ProfileService;
import com.ridepool.service.RideService;
import com.ridepool.service.RideServiceImpl;
import com.ridepool.service.VehicleService;

import java.time.LocalDateTime;
import java.util.Arrays;

public class App {

    public static void main(String[] args) {
        ProfileService profileService = ProfileService.getInstance();
        RideService rideService = RideServiceImpl.getInstance();
        VehicleService vehicleService = VehicleService.getInstance();

        profileService.addDriver(new Driver(1,"Ashish","abcde@gmail.com",7073818968L,""));
        profileService.addUSer(new User(2,"Raj","abcde@gmail.com",7302202191L))

        ;
        vehicleService.addVehicle(new Vehicle(1,"efd2d24d3243d", VehicleType.COMPACT,"alto"),1);

        Ride ride = new Ride(1,new Location(1,"Banglore"), new Location(1,"Patna"), Arrays.asList(),
                LocalDateTime.now().plusHours(5), 1, RideStatus.LISTED, 2);

        rideService.addRide(ride);
        rideService.getRides(new Location(1,"Banglore"), new Location(1,"Patna"),LocalDateTime.now());

    }
}
