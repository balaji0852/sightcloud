package com.sight.sightcloud;

import com.sight.sightcloud.model.projectSetting;
import com.sight.sightcloud.service.projectSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
public class projectSettingController {


    @Autowired
    private projectSettingService projectSettingService;



    @GetMapping(path = "api/projectSetting/{projectStoreID}")
    public projectSetting getProjectSetting(@PathVariable int projectStoreID){
        return projectSettingService.getSettingByProjectStoreID(projectStoreID);
    }

    @PutMapping(path = "api/projectSetting")
    public ResponseEntity<String> updateProjectSetting(@RequestBody projectSetting projectSetting){
        try{
            //todo : need to validate project existence, we use for internal updates. avoid validation
           if( projectSettingService.insertSetting(projectSetting))
               return ResponseEntity.status(200).body("success");

            return ResponseEntity.status(400).body("oops");

        }catch (Exception e){

            return ResponseEntity.status(500).body("oops");
        }
    }



}
