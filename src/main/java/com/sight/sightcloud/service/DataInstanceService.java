package com.sight.sightcloud.service;

import com.sight.sightcloud.model.ClassMaster;
import com.sight.sightcloud.model.DataInstanceMaster;
import com.sight.sightcloud.model.projectSetting;
import com.sight.sightcloud.repository.DataInstanceMasterRepository;
import com.sight.sightcloud.repository.classMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import com.sight.sightcloud.repository.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class DataInstanceService {

    private final DataInstanceMasterRepository dataInstanceMasterRepository;

    private final classMasterRepository classMasterRepository;

    private final projectSettingService projectSettingService;

    @Autowired
    public DataInstanceService(DataInstanceMasterRepository dataInstanceMasterRepository, com.sight.sightcloud.repository.classMasterRepository classMasterRepository, com.sight.sightcloud.service.projectSettingService projectSettingService) {
        this.dataInstanceMasterRepository = dataInstanceMasterRepository;
        this.classMasterRepository = classMasterRepository;
        this.projectSettingService = projectSettingService;
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



    public boolean updateDataInstanceMaster(DataInstanceMaster dataInstanceMaster){
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
        ClassMaster classMaster = classMasterRepository.findByItemMasterID(itemMasterID);

        //12/27/2022: balaji adding this to route project wide cfmw
        projectSetting projectSetting = projectSettingService.getSettingByProjectStoreID(classMaster.getProjectStore().getProjectStoreID());

        if(isPresentDay(dateTimeEpoch) && (classMaster.isCarryForwardMyWork() || projectSetting.isCarryForwardMyWork())) {
            return dataInstanceMasterRepository.findDataInstanceByItemMasterID(itemMasterID);
        }else if(!isPresentDay(dateTimeEpoch) && (classMaster.isCarryForwardMyWork() || projectSetting.isCarryForwardMyWork())) {
            return dataInstanceMasterRepository.findDataInstanceByItemMasterID2(dateTimeEpoch,zeroDateTimeEpoch,itemMasterID);
        }


        return dataInstanceMasterRepository.findDataInstanceByOneInterval(dateTimeEpoch,zeroDateTimeEpoch,itemMasterID);
    }

    public List<DataInstanceMaster> findDataInstanceByIntervalWithClassMaster(Long dateTimeEpoch, Long zeroDateTimeEpoch,int projectStoreID){
        projectSetting projectSetting = projectSettingService.getSettingByProjectStoreID(projectStoreID);

        if(isPresentDay(dateTimeEpoch) && projectSetting.isCarryForwardMyWork()){
            return dataInstanceMasterRepository.findDataInstanceByProjectStoreID(projectStoreID);
        }else if(!isPresentDay(dateTimeEpoch) && projectSetting.isCarryForwardMyWork()){
            return dataInstanceMasterRepository.findDataInstanceByProjectStoreID2(projectStoreID,dateTimeEpoch,zeroDateTimeEpoch);
        }

        return dataInstanceMasterRepository.findDataInstanceByIntervalWithClassMaster(dateTimeEpoch,zeroDateTimeEpoch,projectStoreID);
    }

    public List<DataInstanceMaster> findDataInstanceByOneIntervalV1(Long dateTimeEpoch, Long zeroDateTimeEpoch, int itemMasterID,int instancesStatus){
        ClassMaster classMaster = classMasterRepository.findByItemMasterID(itemMasterID);

        //12/27/2022: balaji adding this to route project wide cfmw
        projectSetting projectSetting = projectSettingService.getSettingByProjectStoreID(classMaster.getProjectStore().getProjectStoreID());

        if(isPresentDay(dateTimeEpoch) && (classMaster.isCarryForwardMyWork() || projectSetting.isCarryForwardMyWork())) {
            return dataInstanceMasterRepository.findDataInstanceByItemMasterIDAndStatus(itemMasterID,instancesStatus);

        }else if(!isPresentDay(dateTimeEpoch) && (classMaster.isCarryForwardMyWork() || projectSetting.isCarryForwardMyWork())) {
            return dataInstanceMasterRepository.findDataInstanceByItemMasterIDAndStatus2(dateTimeEpoch,zeroDateTimeEpoch,itemMasterID,instancesStatus);

        }

        return dataInstanceMasterRepository.findDataInstanceByOneIntervalV1(dateTimeEpoch,zeroDateTimeEpoch,itemMasterID,instancesStatus);
    }

    public List<DataInstanceMaster> findDataInstanceByIntervalWithClassMasterV1(Long dateTimeEpoch,Long zeroDateTimeEpoch,int instancesStatus,int projectStoreID){
        projectSetting projectSetting = projectSettingService.getSettingByProjectStoreID(projectStoreID);

        if(isPresentDay(dateTimeEpoch) && projectSetting.isCarryForwardMyWork()){
            return dataInstanceMasterRepository.findDataInstanceByProjectStoreIDAndStatus(projectStoreID,instancesStatus);
        }else if(!isPresentDay(dateTimeEpoch) && projectSetting.isCarryForwardMyWork()){
            return dataInstanceMasterRepository.findDataInstanceByProjectStoreIDAndStatus2(projectStoreID,dateTimeEpoch,zeroDateTimeEpoch,instancesStatus);
        }

        return dataInstanceMasterRepository.findDataInstanceByIntervalWithClassMasterV1(dateTimeEpoch,zeroDateTimeEpoch,instancesStatus,projectStoreID);
    }


    public Optional<DataInstanceMaster> findLastCommentByItemMasterID(int itemMasterID){
        return dataInstanceMasterRepository.findDataInstanceByLastComment(itemMasterID);
    }


    private boolean isPresentDay(Long dateTimeEpoch){
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(dateTimeEpoch/1000,0,ZoneOffset.of("+05:30") );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d,yyyy", Locale.ENGLISH);
        String date = formatter.format(dateTime);
        System.out.println(formatter.format(dateTime));
        return date.equals(formatter.format(LocalDateTime.now().atOffset(ZoneOffset.of("+05:30"))));
    }
}
