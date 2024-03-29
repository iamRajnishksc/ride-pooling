package com.ridepool.service;

import com.ridepool.model.Vehicle;

import java.util.*;

public class VehicleService {
    private static VehicleService vehicleService;


    private  Map<Integer, List<Vehicle>> vehicleMap;

    private VehicleService(Map<Integer, List<Vehicle>> vehicleMap) {
        this.vehicleMap = vehicleMap;
    }

    public static VehicleService getInstance(){
        if(vehicleService == null){
            init();
        }
        return vehicleService;
    }

    private synchronized static void init() {
        if(vehicleService ==null)
                vehicleService = new VehicleService(new HashMap<>());
    }

    public void  addVehicle(Vehicle vehicle, Integer userId){
        vehicleMap.putIfAbsent(userId,new ArrayList<>());
        vehicleMap.get(userId).add(vehicle);
    }

    public List<Vehicle> getVehicle(String userID){
        return vehicleMap.getOrDefault(userID,Arrays.asList());
    }


}
