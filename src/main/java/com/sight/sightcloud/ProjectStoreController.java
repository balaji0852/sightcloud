package com.sight.sightcloud;

import com.sight.sightcloud.model.ProjectStore;
import com.sight.sightcloud.model.UserManagementStore;
import com.sight.sightcloud.model.UserStore;
import com.sight.sightcloud.model.projectSetting;
import com.sight.sightcloud.repository.classMasterRepository;
import com.sight.sightcloud.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = {"http://localhost:3000","http://edmsadmin.azurewebsites.net"},maxAge = 5000 )
@RestController
public class ProjectStoreController {

    @Autowired
    private projectStoreService projectStoreService;

    @Autowired
    private com.sight.sightcloud.service.projectSettingService projectSettingService;

    @Autowired
    private userManagementStoreService userManagementStoreService;



    @DeleteMapping(path = "/api/projectStore/{projectStoreID}")
    public ResponseEntity<String> deleteProject(@PathVariable int projectStoreID){
        if(projectStoreService.deleteProject(projectStoreID)){
            return ResponseEntity.status(200).body("success");
        }
        return ResponseEntity.status(400).body("internal service error");
    }

    @PostMapping(path = "/api/projectStore")
    public ResponseEntity<String> insertProject(@RequestBody  ProjectStore projectStore,@RequestParam int userStoreID){
        int projectStoreID = projectStoreService.insertProject(projectStore);
        if(projectStoreID!=-1){
            UserManagementStore userManagementStore = new UserManagementStore();
            userManagementStore.setAdmin(true);
            UserStore userStore = new UserStore();
            userStore.setUserStoreID(userStoreID);
            userManagementStore.setUserStore(userStore);
            projectStore.setProjectStoreID(projectStoreID);
            userManagementStore.setProjectStore(projectStore);
            //7/3/2023 : //balaji : 2/25/2023 user management installation adding state and isInvited;
            userManagementStore.setInvited(false);
            //7/3/2023 : balaji : project creator -state=3
            userManagementStore.setState(3);

            //27/12/2022, adding project setting
            projectSetting projectSetting = new projectSetting();
            projectSetting.setProjectStore(projectStore);
            projectSetting.setCarryForwardMyWork(false);



            if(userManagementStoreService.createProjectUser(userManagementStore) && projectSettingService.addSetting(projectSetting)) {
                return ResponseEntity.status(200).body(Integer.toString(projectStoreID));
            }
            return ResponseEntity.status(201).body("success-contact admin");
        }
        return ResponseEntity.status(400).body("internal service error");
    }

    @PutMapping(path = "/api/projectStore")
    public ResponseEntity<String> deleteProject(@RequestBody  ProjectStore projectStore){
        if(projectStoreService.updateProject(projectStore)){
            return ResponseEntity.status(200).body("success");
        }
        return ResponseEntity.status(400).body("internal service error");
    }

    @GetMapping(path = "/api/projectStore/projects")
    public ResponseEntity<List<ProjectStore>> findAllProject(@RequestParam int userStoreID){
        return ResponseEntity.status(200).body(projectStoreService.findAllProjectByUserStoreID(userStoreID));
    }


}
