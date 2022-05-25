package com.sight.sightcloud;


import com.sight.sightcloud.model.DataInstanceMaster;
import com.sight.sightcloud.service.DataInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DataInstanceMasterController {

    @Autowired
    private DataInstanceService dataInstanceService;

    @GetMapping(path = "/api/dataInstanceMaster")
    public List<DataInstanceMaster> getAllDataInstanceMaster(){
        return dataInstanceService.getAllDataInstanceMaster();
    }


    @PostMapping(path = "/api/dataInstanceMaster")
    public ResponseEntity insertDataInstanceMaster(@RequestBody DataInstanceMaster dataInstanceMaster){
        //dataInstanceMaster.setDataInstanceID(3);
        boolean responseFlag = dataInstanceService.insertDataInstanceMaster(dataInstanceMaster);

        if(responseFlag){
            return ResponseEntity.status(200).body("success");
        }

        return ResponseEntity.status(500).body("internal service error");
    }

    @DeleteMapping(path = "/api/dataInstanceMaster/{dataInstanceID}")
    public ResponseEntity deleteDataInstanceMaster(@PathVariable int dataInstanceID){
        boolean responseFlag = dataInstanceService.deleteDataInstanceMaster(dataInstanceID);
        if(responseFlag){
            return ResponseEntity.status(200).body("success");
        }

        return ResponseEntity.status(500).body("internal service error");
    }
}
