package com.foodkart.service;

import com.foodkart.model.Driver;
import com.foodkart.model.User;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ProfileService {
    private static ProfileService profileService;

    private Map<Integer, Driver> driverMap;
    private Map<Integer, User> userMap;

    private ProfileService(Map<Integer, Driver> driverMap, Map<Integer, User> userMap) {
        this.driverMap = driverMap;
        this.userMap = userMap;
    }
    public static ProfileService getInstance(){
        if(profileService == null){
            init();
        }
        return profileService;
    }

    private  synchronized static void init() {
        if(profileService==null)
            profileService = new ProfileService(new HashMap<>(),new HashMap<>());
    }


    public Map<Integer, Driver> getDriverMap() {
        return driverMap;
    }

    public Map<Integer, User> getUserMap() {
        return userMap;
    }

    public void addDriver(Driver driver){
        this.driverMap.put(driver.getId(),driver);
    }

    public void addUSer(User user){
        this.userMap.put(user.getId(),user);
    }
}
