package com.sight.sightcloud.service;

import com.sight.sightcloud.model.ClassMaster;
import com.sight.sightcloud.model.DataInstanceMaster;
import com.sight.sightcloud.repository.DataInstanceMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataInstanceService {

    private final DataInstanceMasterRepository dataInstanceMasterRepository;

    @Autowired
    DataInstanceService(DataInstanceMasterRepository dataInstanceMasterRepository){
        this.dataInstanceMasterRepository = dataInstanceMasterRepository;
    }

    public List<DataInstanceMaster> getAllDataInstanceMaster(){
        return dataInstanceMasterRepository.findAll();
    }

    public boolean insertDataInstanceMaster(DataInstanceMaster dataInstanceMaster){
        try{
            dataInstanceMasterRepository.save(dataInstanceMaster);
           return true;
        }catch (Exception e){

        }

        return false;
    }

    public Boolean deleteDataInstanceMaster(int dataInstanceID){
        DataInstanceMaster dataInstanceMaster = dataInstanceMasterRepository.getById(dataInstanceID);
        if(dataInstanceMasterRepository.existsById(dataInstanceID)){
            dataInstanceMasterRepository.delete(dataInstanceMaster);
            return  true;
        }
        return  false;
    }

    public List<DataInstanceMaster> findDataInstanceByOneInterval(int dateTimeEpoch, int zeroDateTimeEpoch, int itemMasterID){
        return dataInstanceMasterRepository.findDataInstanceByOneInterval(dateTimeEpoch,zeroDateTimeEpoch,itemMasterID);
    }

    public List<DataInstanceMaster> findDataInstanceByIntervalWithClassMaster(int dateTimeEpoch, int zeroDateTimeEpoch){
        return dataInstanceMasterRepository.findDataInstanceByIntervalWithClassMaster(dateTimeEpoch,zeroDateTimeEpoch);
    }
}
