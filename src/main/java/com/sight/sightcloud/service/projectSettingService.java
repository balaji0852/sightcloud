package com.sight.sightcloud.service;


import com.sight.sightcloud.model.projectSetting;
import com.sight.sightcloud.repository.projectSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class projectSettingService {

    private final projectSettingRepository projectSettingRepository;


    @Autowired
    public projectSettingService(com.sight.sightcloud.repository.projectSettingRepository projectSettingRepository) {
        this.projectSettingRepository = projectSettingRepository;
    }





    public projectSetting getSettingByProjectStoreID(int projectStoreID){
        return projectSettingRepository.findByProjectStoreID(projectStoreID);
    }

    public boolean addSetting(projectSetting _projectSetting){
        try{
            projectSettingRepository.save(_projectSetting);
            return true;
        }catch (Exception e){
            return false;
        }
    }



    public boolean insertSetting(projectSetting _projectSetting){
        try{
            if(projectSettingRepository.existsById(_projectSetting.getProjectSettingID())) {
                projectSettingRepository.save(_projectSetting);
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }
    }


}
