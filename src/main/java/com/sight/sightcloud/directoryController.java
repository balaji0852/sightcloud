package com.sight.sightcloud;


import com.sight.sightcloud.model.pinnedClass;
import com.sight.sightcloud.service.directoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class directoryController {

    @Autowired
    private directoryService directoryService;


    @PutMapping("/api/classMaster/directory")
    public ResponseEntity<String> updateDirectory(@RequestBody pinnedClass pinnedClass){
        if(directoryService.updateDirectory(pinnedClass)){
            return ResponseEntity.status(200).body("updated directory");
        }

        return ResponseEntity.status(500).body("something went wrong");

    }


    @GetMapping("/api/classMaster/getAllDirectories")
    public List<pinnedClass> getAllDirectories(){
        return directoryService.getAllDirectories();
    }

}
