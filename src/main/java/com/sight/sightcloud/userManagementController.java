package com.sight.sightcloud;


import com.sight.sightcloud.model.UserManagementStore;
import com.sight.sightcloud.model.UserStore;
import com.sight.sightcloud.service.userManagementStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class userManagementController {


    private final userManagementStoreService userManagmentStoreService;

    @Autowired
    public userManagementController(userManagementStoreService userManagmentStoreService) {
        this.userManagmentStoreService = userManagmentStoreService;
    }

    @GetMapping(path = "/api/getProjectMembers")
    public List<UserManagementStore> getAllUserInProject(@RequestParam int projectStoreID){
        return userManagmentStoreService.getAllUserFromProject(projectStoreID);
    }


    @PutMapping(path = "/api/updateOrDeleteUser")
    public UserManagementStore updateUser(@RequestBody UserManagementStore userManagementStore){
        return userManagmentStoreService.updateUser(userManagementStore);
    }

    @PostMapping(path = "/api/addUserToProject")
    public ResponseEntity<String> addUser(@RequestParam String inviteeMail,@RequestParam int projectStoreID){

        int Response = userManagmentStoreService.addUser(inviteeMail,projectStoreID);
        return ResponseEntity.status(Response).body(" ");
    }
}
