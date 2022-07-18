package com.sight.sightcloud;

import com.sight.sightcloud.model.ProjectStore;
import com.sight.sightcloud.service.projectStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProjectStoreController {

    @Autowired
    private projectStoreService projectStoreService;

    @DeleteMapping(path = "/api/projectStore/{projectStoreID}")
    public ResponseEntity<String> deleteProject(@PathVariable int projectStoreID){
        if(projectStoreService.deleteProject(projectStoreID)){
            return ResponseEntity.status(200).body("success");
        }
        return ResponseEntity.status(400).body("internal service error");
    }

    @PostMapping(path = "/api/projectStore")
    public ResponseEntity<String> insertProject(@RequestBody  ProjectStore projectStore){
        if(projectStoreService.insertProject(projectStore)){
            return ResponseEntity.status(200).body("success");
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

    @GetMapping(path = "/api/projectStore")
    public ResponseEntity<Optional<ProjectStore>> findAllUsersProject(@RequestParam int userStoreID){
        return ResponseEntity.status(200).body(projectStoreService.findAllProjectByUserStoreID(userStoreID));
    }

}
