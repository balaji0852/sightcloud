package com.sight.sightcloud.service;


import com.sight.sightcloud.model.ProjectStore;
import com.sight.sightcloud.model.UserManagementStore;
import com.sight.sightcloud.repository.userManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sight.sightcloud.repository.projectStoreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class projectStoreService {

    private final projectStoreRepository projectStoreRepository;

   // private final userManagementRepository userManagementRepository;

    @Autowired
    public projectStoreService(projectStoreRepository projectStoreRepository) {
        this.projectStoreRepository = projectStoreRepository;
        //this.userManagementRepository = userManagementRepository;
    }


//    public Optional<ProjectStore> findAllProjectByUserStoreID(int userStoreID){
//        return projectStoreRepository.findAllByUserManagementStoreUserStoreID(userStoreID);
//    }

    public int insertProject(ProjectStore projectStore){
        try{
           return projectStoreRepository.save(projectStore).getProjectStoreID();
        }catch (Exception e){
            return -1;
        }
    }

    public boolean deleteProject(int projectStoreID){
        try{
            if(projectStoreRepository.existsById(projectStoreID)) {
                ProjectStore projectStore = projectStoreRepository.findByprojectStoreID(projectStoreID);
                projectStoreRepository.delete(projectStore);
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }

    public boolean updateProject(ProjectStore projectStore){
        try{
            projectStoreRepository.save(projectStore);
            return true;
        }catch (Exception e){
            return false;
        }
    }



    public List<ProjectStore> findAllProjectByUserStoreID(int userStoreID){
//        UserManagementStore userManagementStore =
        return projectStoreRepository.findAllByUserManagementStore_UserStoreID(userStoreID);
    }
}
