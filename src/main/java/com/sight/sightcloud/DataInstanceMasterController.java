package com.sight.sightcloud;


import com.sight.sightcloud.model.ClassMaster;
import com.sight.sightcloud.model.DataInstanceMaster;
import com.sight.sightcloud.model.dataInstanceMasterVO;
import com.sight.sightcloud.service.DataInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<String> insertDataInstanceMaster(@RequestBody dataInstanceMasterVO dataInstanceMasterVO){
        DataInstanceMaster dataInstanceMaster = new DataInstanceMaster();
        dataInstanceMaster.setDataInstances(dataInstanceMasterVO.getDataInstances());
        dataInstanceMaster.setInstanceTime(Long.parseLong(dataInstanceMasterVO.getInstanceTime()));
        dataInstanceMaster.setClassMaster(dataInstanceMasterVO.getClassMaster());
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
