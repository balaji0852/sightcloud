package com.sight.sightcloud;


import com.sight.sightcloud.model.DataInstanceMaster;
import com.sight.sightcloud.service.DataInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/v1")
public class DataInstanceMasterController {

    @Autowired
    private DataInstanceService dataInstanceService;

    @GetMapping(path = "/api/dataInstanceMaster")
    public List<DataInstanceMaster> getAllDataInstanceMaster(){
        return dataInstanceService.getAllDataInstanceMaster();
    }


    @PostMapping(path = "/api/dataInstanceMaster")
    public ResponseEntity<String> insertDataInstanceMaster(@RequestBody DataInstanceMaster dataInstanceMaster){
        boolean responseFlag = dataInstanceService.insertDataInstanceMaster(dataInstanceMaster);

        if(responseFlag){
            return ResponseEntity.status(200).body("success");
        }

        return ResponseEntity.status(500).body("internal service error");
    }

    @DeleteMapping(path = "/api/dataInstanceMaster/{dataInstanceID}")
    public ResponseEntity<String> deleteDataInstanceMaster(@PathVariable int dataInstanceID){
        boolean responseFlag = dataInstanceService.deleteDataInstanceMaster(dataInstanceID);
        if(responseFlag){
            return ResponseEntity.status(200).body("success");
        }

        return ResponseEntity.status(500).body("internal service error");
    }

    @GetMapping(path = "/api/dataInstanceMaster/query1")
    public List<DataInstanceMaster> findDataInstanceByOneInterval(@RequestParam int dateTimeEpoch,@RequestParam int zeroDateTimeEpoch,@RequestParam int itemMasterID){
        return dataInstanceService.findDataInstanceByOneInterval(dateTimeEpoch,zeroDateTimeEpoch,itemMasterID);
    }

    @GetMapping(path = "/api/dataInstanceMaster/query2")
    public List<DataInstanceMaster> findDataInstanceByIntervalWithClassMaster(@RequestParam int dateTimeEpoch,@RequestParam int zeroDateTimeEpoch){
        return dataInstanceService.findDataInstanceByIntervalWithClassMaster(dateTimeEpoch,zeroDateTimeEpoch);
    }
}
