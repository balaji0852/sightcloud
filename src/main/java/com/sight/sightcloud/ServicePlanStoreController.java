package com.sight.sightcloud;


import com.sight.sightcloud.model.ServicePlanStore;
import com.sight.sightcloud.service.ServStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServicePlanStoreController {


    @Autowired
    private ServStoreService servStoreService;




    @GetMapping(path = "/api/servicePlanStore")
    public List<ServicePlanStore> getAllServicePlanStore(){


        return servStoreService.getAllService();
    }

}
