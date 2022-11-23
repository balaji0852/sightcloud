package com.sight.sightcloud.service;


import com.sight.sightcloud.model.ServicePlanStore;
import com.sight.sightcloud.repository.ServiceStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServStoreService {

    private final ServiceStoreRepository serviceStoreRepository;

    @Autowired
    public ServStoreService(ServiceStoreRepository serviceStoreRepository) {
        this.serviceStoreRepository = serviceStoreRepository;
    }


    public List<ServicePlanStore> getAllService(){
        return serviceStoreRepository.findAll();
    }
}
