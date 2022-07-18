package com.sight.sightcloud.service;

import com.sight.sightcloud.model.ClassMaster;
import com.sight.sightcloud.repository.classMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class classMasterService {

    private final classMasterRepository classMasterRepository;

    @Autowired
    public classMasterService(classMasterRepository classMasterRepository) {
        this.classMasterRepository = classMasterRepository;
    }

    public Optional<ClassMaster> findClassMasterByProjectStoreID(int projectStoreID){
        return classMasterRepository.findByProjectStoreID(projectStoreID);
    }

    public List<ClassMaster> getClassMaster(){
        return classMasterRepository.findAll();
    }

    public Boolean postClassMaster(ClassMaster classMaster){
        classMasterRepository.save(classMaster);
        return true;
    }

    public Boolean deleteClassMaster(int itemMasterID){
        if(classMasterRepository.existsById(itemMasterID)){
            ClassMaster classMaster = classMasterRepository.getById(itemMasterID);
            classMasterRepository.delete(classMaster);
          return  true;
        }
        return  false;
    }

    public Boolean updateClassMaster(ClassMaster classMaster){
        if(classMasterRepository.existsById(classMaster.getItemMasterID())){
            classMasterRepository.save(classMaster);
            return true;
        }
        return  false;
    }

}
