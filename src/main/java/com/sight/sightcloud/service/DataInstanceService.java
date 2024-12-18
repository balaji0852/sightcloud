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
import java.util.*;

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
        //22/03/2023 : balaji : sale 24; commenting down || projectSetting.isCarryForwardMyWork()
        if(isPresentDay(dateTimeEpoch) && (classMaster.isCarryForwardMyWork())) {
            //01/12/2023 :balaji , bug 1 <- adding this fix here
            List<DataInstanceMaster> response = dataInstanceMasterRepository.findDataInstanceByItemMasterID(itemMasterID);
            response.addAll(dataInstanceMasterRepository.findDataInstanceByOneIntervalV1(dateTimeEpoch,zeroDateTimeEpoch,itemMasterID,3));
            return response;

        }else if(!isPresentDay(dateTimeEpoch) && (classMaster.isCarryForwardMyWork())) {
            return dataInstanceMasterRepository.findDataInstanceByItemMasterID2(dateTimeEpoch,zeroDateTimeEpoch,itemMasterID);
        }


        return dataInstanceMasterRepository.findDataInstanceByOneInterval(dateTimeEpoch,zeroDateTimeEpoch,itemMasterID);
    }

    public List<DataInstanceMaster> findDataInstanceByIntervalWithClassMaster(Long dateTimeEpoch, Long zeroDateTimeEpoch,int projectStoreID){
        projectSetting projectSetting = projectSettingService.getSettingByProjectStoreID(projectStoreID);

        if(isPresentDay(dateTimeEpoch) && projectSetting.isCarryForwardMyWork()){
            //01/12/2023 :balaji , bug 1 <- adding this fix here
            List<DataInstanceMaster> response = dataInstanceMasterRepository.findDataInstanceByProjectStoreID(projectStoreID);
            response.addAll(dataInstanceMasterRepository.findDataInstanceByIntervalWithClassMasterV1(dateTimeEpoch,zeroDateTimeEpoch,3,projectStoreID));
            return response;

        }else if(!isPresentDay(dateTimeEpoch) && projectSetting.isCarryForwardMyWork()){

            return dataInstanceMasterRepository.findDataInstanceByProjectStoreID2(projectStoreID,dateTimeEpoch,zeroDateTimeEpoch);
        }

        return dataInstanceMasterRepository.findDataInstanceByIntervalWithClassMaster(dateTimeEpoch,zeroDateTimeEpoch,projectStoreID);
    }

    public List<DataInstanceMaster> findDataInstanceByOneIntervalV1(Long dateTimeEpoch, Long zeroDateTimeEpoch, int itemMasterID,int instancesStatus){
        ClassMaster classMaster = classMasterRepository.findByItemMasterID(itemMasterID);

        //12/27/2022: balaji adding this to route project wide cfmw
        projectSetting projectSetting = projectSettingService.getSettingByProjectStoreID(classMaster.getProjectStore().getProjectStoreID());
        //22/03/2023 : balaji : sale 24; commenting down || projectSetting.isCarryForwardMyWork()
        if(isPresentDay(dateTimeEpoch) && (classMaster.isCarryForwardMyWork() )) {
            //01/12/2023 :balaji , bug 1 <- adding this fix here
            List<DataInstanceMaster> response = dataInstanceMasterRepository.findDataInstanceByItemMasterIDAndStatus(itemMasterID,instancesStatus);
            if(instancesStatus==3) {
                response.addAll(dataInstanceMasterRepository.findDataInstanceByOneIntervalV1(dateTimeEpoch, zeroDateTimeEpoch, itemMasterID, 3));
            }
            return response;

        }else if(!isPresentDay(dateTimeEpoch) && (classMaster.isCarryForwardMyWork())) {
            //22/03/2023 : balaji , bug 8, adding cases for state 1 and 2, response should be [] empty, since cmfw is on
            List<DataInstanceMaster> empty = new ArrayList<>();
            if(instancesStatus==2 || instancesStatus==1)
                return empty;
            return dataInstanceMasterRepository.findDataInstanceByItemMasterIDAndStatus2(dateTimeEpoch,zeroDateTimeEpoch,itemMasterID,instancesStatus);

        }

        return dataInstanceMasterRepository.findDataInstanceByOneIntervalV1(dateTimeEpoch,zeroDateTimeEpoch,itemMasterID,instancesStatus);
    }

    public List<DataInstanceMaster> findDataInstanceByIntervalWithClassMasterV1(Long dateTimeEpoch,Long zeroDateTimeEpoch,int instancesStatus,int projectStoreID){
        projectSetting projectSetting = projectSettingService.getSettingByProjectStoreID(projectStoreID);

        if(isPresentDay(dateTimeEpoch) && projectSetting.isCarryForwardMyWork()){
            //01/12/2023 :balaji , bug 1 <- adding this fix here
            List<DataInstanceMaster> response = dataInstanceMasterRepository.findDataInstanceByProjectStoreIDAndStatus(projectStoreID,instancesStatus);
            if(instancesStatus==3) {
                response.addAll(dataInstanceMasterRepository.findDataInstanceByIntervalWithClassMasterV1(dateTimeEpoch, zeroDateTimeEpoch, 3, projectStoreID));
            }
            return response;

        }else if(!isPresentDay(dateTimeEpoch) && projectSetting.isCarryForwardMyWork()){
            //22/03/2023 : balaji , bug 8, adding cases for state 1 and 2, response should be [] empty, since cmfw is on
            List<DataInstanceMaster> empty = new ArrayList<>();
            if(instancesStatus==2 || instancesStatus==1)
                return empty;
             return dataInstanceMasterRepository.findDataInstanceByProjectStoreIDAndStatus2(projectStoreID,dateTimeEpoch,zeroDateTimeEpoch,instancesStatus);
        }

        return dataInstanceMasterRepository.findDataInstanceByIntervalWithClassMasterV1(dateTimeEpoch,zeroDateTimeEpoch,instancesStatus,projectStoreID);
    }


    public Optional<DataInstanceMaster> findLastCommentByItemMasterID(int itemMasterID){
        return dataInstanceMasterRepository.findDataInstanceByLastComment(itemMasterID);
    }


    private boolean isPresentDay(Long dateTimeEpoch){
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(dateTimeEpoch/1000,0,ZoneOffset.of("+00:00") );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d,yyyy", Locale.ENGLISH);
        String date = formatter.format(dateTime);
        System.out.println(dateTimeEpoch/1000+" - "+dateTimeEpoch+" isPresentDay : icming time"+date+" - device time-"+formatter.format(LocalDateTime.now()));
        return date.equals(formatter.format(LocalDateTime.now()));
    }
}
