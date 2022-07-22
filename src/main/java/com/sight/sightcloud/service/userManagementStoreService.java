package com.sight.sightcloud.service;

import com.sight.sightcloud.model.UserManagementStore;
import com.sight.sightcloud.repository.userManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userManagementStoreService {

    private final userManagementRepository userManagementRepository;

    @Autowired
    public userManagementStoreService(userManagementRepository userManagementRepository) {
        this.userManagementRepository = userManagementRepository;
    }


    public boolean createProjectUser(UserManagementStore userManagementStore){
        try{
           userManagementRepository.save(userManagementStore);
           return true;
        }catch (Exception e){
            return false;
        }
    }
}
