package com.ridepool.model;

import com.ridepool.enums.VehicleType;

public class Vehicle {
    public final Integer id;
    public final  String rc;
    public final VehicleType vehicleType;
    public final String model;

    public Vehicle(Integer id, String rc, VehicleType vehicleType, String model) {
        this.id = id;
        this.rc = rc;
        this.vehicleType = vehicleType;
        this.model = model;
    }

    public Integer getId() {
        return id;
    }

    public String getRc() {
        return rc;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getModel() {
        return model;
    }
}
