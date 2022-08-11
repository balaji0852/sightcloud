package com.sight.sightcloud;

import com.sight.sightcloud.model.ProjectStore;
import com.sight.sightcloud.model.UserManagementStore;
import com.sight.sightcloud.model.UserStore;
import com.sight.sightcloud.service.projectStoreService;
import com.sight.sightcloud.service.userManagementStoreService;
import com.sight.sightcloud.service.userStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000","http://edmsadmin.azurewebsites.net"},maxAge = 5000 )
@RestController
public class ProjectStoreController {

    @Autowired
    private projectStoreService projectStoreService;

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
            if(userManagementStoreService.createProjectUser(userManagementStore)) {
                return ResponseEntity.status(200).body("success");
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
