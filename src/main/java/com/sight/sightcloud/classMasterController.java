package com.sight.sightcloud;
import com.sight.sightcloud.model.ClassMaster;
import com.sight.sightcloud.service.classMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class classMasterController {

    @Autowired
    private classMasterService classMasterService;

    @GetMapping("/api/classMaster")
    public List<ClassMaster> getAllClassMaster() {
        return classMasterService.getClassMaster();
    }


    @GetMapping("/api/classMaster")
    public Optional<ClassMaster> getAllByProjectStoreID(@RequestBody int projectStoreID) {
        return classMasterService.findClassMasterByProjectStoreID(projectStoreID);
    }

    @PostMapping("/api/classMaster")
    public ResponseEntity<String> postClassMaster(@RequestBody ClassMaster classMaster){
        Boolean responseFlag = classMasterService.postClassMaster(classMaster);

        if(responseFlag)
            return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.OK).body("success");

        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("oops");
    }

    @DeleteMapping(path = "/api/classMaster/{itemMasterID}")
    public ResponseEntity<String> deleteClassMaster(@PathVariable int itemMasterID){
        Boolean responseFlag = classMasterService.deleteClassMaster(itemMasterID);

        if(responseFlag)
            return ResponseEntity.status(HttpStatus.OK).body("success");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("oops");
    }

    @PutMapping("/api/classMaster")
    public ResponseEntity<String> updateClassMaster(@RequestBody ClassMaster classMaster){
        Boolean responseFlag = classMasterService.updateClassMaster(classMaster);

        if(responseFlag)
            return ResponseEntity.status(HttpStatus.OK).body("success");

        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("oops");
    }
}
