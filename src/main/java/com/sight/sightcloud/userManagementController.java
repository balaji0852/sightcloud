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

    //9/3/2023 : Balaji - adding validation for email ,and removing white space
    @PostMapping(path = "/api/addUserToProject")
        public ResponseEntity<String> addUser(@RequestParam String inviteeMail,@RequestParam int projectStoreID){
        inviteeMail.replaceAll("\\s","");
        if(!inviteeMail.contains("@") || !inviteeMail.contains(".")){
            return ResponseEntity.status(400).body("bad request {check the email for typos}");
        }


        int Response = userManagmentStoreService.addUser(inviteeMail,projectStoreID);
        return ResponseEntity.status(Response).body(" ");
    }
}
