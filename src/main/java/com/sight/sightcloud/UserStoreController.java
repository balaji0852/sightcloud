package com.sight.sightcloud;

import com.sight.sightcloud.model.UserStore;
import com.sight.sightcloud.service.userStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserStoreController {

    @Autowired
    private userStoreService userStoreService;

    //balaji : may be need to check for presence of duplicate entity
    @PostMapping(path = "/api/userStore")
    public ResponseEntity<String> addUser(@RequestBody UserStore userStore){
       if(userStoreService.findForDuplicateEmail(userStore.getLinkedEmail())) {
            if (userStoreService.insertUser(userStore)) {
                return ResponseEntity.ok().body("success");
            }
       }else{
            return ResponseEntity.status(400).body("Bad Request");
        }
        return ResponseEntity.status(500).body("internal service error");
    }

    @GetMapping(path = "/api/userStore/linkedEmail/{linkedEmail}")
    public ResponseEntity<UserStore> findUserByEmail(@PathVariable String linkedEmail){
        return ResponseEntity.status(200).body(userStoreService.findUserByEmail(linkedEmail));
    }
    @GetMapping(path = "/api/userStore/linkedPhone/{linkedPhone}")
    public ResponseEntity<List<UserStore>> findUserByPhone(@PathVariable String linkedPhone){
        return ResponseEntity.status(200).body(userStoreService.findUserByPhone(linkedPhone));
    }

    //will be used for userAbout
    @GetMapping(path = "/api/userStore/userStoreID/{userStoreID}")
    public ResponseEntity<UserStore> findUserByUserStoreID(@PathVariable int userStoreID){
        return ResponseEntity.status(200).body(userStoreService.findUserByUserStoreID(userStoreID));
    }

    @DeleteMapping(path = "/api/userStore/{userStoreID}")
    public ResponseEntity<String> deleteUser(@PathVariable int userStoreID){
        if(userStoreService.deleteUser(userStoreID)){
            return ResponseEntity.status(200).body("success");
        }
        return ResponseEntity.status(400).body("internal service error");
    }

    @PutMapping(path = "/api/userStore")
    public ResponseEntity<String> updateUser(@RequestBody UserStore userStore){
        if(userStoreService.updateUser(userStore)){
            return ResponseEntity.status(200).body("success");
        }
        return ResponseEntity.status(400).body("internal service error");
    }
}
