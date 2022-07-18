package com.sight.sightcloud.service;

import com.sight.sightcloud.model.ClassMaster;
import com.sight.sightcloud.model.DataInstanceMaster;
import com.sight.sightcloud.repository.DataInstanceMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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

    public List<DataInstanceMaster> findDataInstanceByOneInterval(Long dateTimeEpoch, Long zeroDateTimeEpoch, int itemMasterID){
        return dataInstanceMasterRepository.findDataInstanceByOneInterval(dateTimeEpoch,zeroDateTimeEpoch,itemMasterID);
    }

    public List<DataInstanceMaster> findDataInstanceByIntervalWithClassMaster(Long dateTimeEpoch, Long zeroDateTimeEpoch,int projectStoreID){
        return dataInstanceMasterRepository.findDataInstanceByIntervalWithClassMaster(dateTimeEpoch,zeroDateTimeEpoch,projectStoreID);
    }

    public List<DataInstanceMaster> findDataInstanceByOneIntervalV1(Long dateTimeEpoch, Long zeroDateTimeEpoch, int itemMasterID,int instancesStatus){
        return dataInstanceMasterRepository.findDataInstanceByOneIntervalV1(dateTimeEpoch,zeroDateTimeEpoch,itemMasterID,instancesStatus);
    }

    public List<DataInstanceMaster> findDataInstanceByIntervalWithClassMasterV1(Long dateTimeEpoch,Long zeroDateTimeEpoch,int instancesStatus,int projectStoreID){
        return dataInstanceMasterRepository.findDataInstanceByIntervalWithClassMasterV1(dateTimeEpoch,zeroDateTimeEpoch,instancesStatus,projectStoreID);
    }


    public Optional<DataInstanceMaster> findLastCommentByItemMasterID(int itemMasterID){
        return dataInstanceMasterRepository.findDataInstanceByLastComment(itemMasterID);
    }
}
