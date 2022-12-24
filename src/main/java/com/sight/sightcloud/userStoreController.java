package com.sight.sightcloud;


import com.sight.sightcloud.model.UserStore;
import com.sight.sightcloud.service.userStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userStoreController {

    @Autowired
    private com.sight.sightcloud.service.userStoreService userStoreService;


    @PostMapping(path = "/api/userStore")
    public ResponseEntity<String> AddUser(@RequestBody UserStore userStore){
        try{
            userStoreService.insertUser(userStore);
            return ResponseEntity.status(200).body("success");
        }catch (Exception e){
            return ResponseEntity.status(500).body("try again later");
        }

    }


    @PutMapping(path = "/api/userStore/update")
    public ResponseEntity<String> updateUser(@RequestBody UserStore userStore){
        try{
            userStoreService.insertUser(userStore);
            return ResponseEntity.status(200).body("success");
        }catch (Exception e){
            return ResponseEntity.status(500).body("try again later");
        }

    }


}
