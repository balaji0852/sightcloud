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
            if(userStoreService.insertUser(userStore)) {
                return ResponseEntity.status(200).body("success");
            }
            return  ResponseEntity.status(201).body("user exist");
        }catch (Exception e){
            return ResponseEntity.status(500).body("try again later");
        }

    }


    @PutMapping(path = "/api/userStore/update")
    public ResponseEntity<UserStore> updateUser(@RequestBody UserStore userStore){
        UserStore _userStore = new UserStore();
        try{
            _userStore = userStoreService.updateUser(userStore);
            return ResponseEntity.status(200).body(_userStore);
        }catch (Exception e){
            return ResponseEntity.status(500).body(_userStore);
        }

    }


}
