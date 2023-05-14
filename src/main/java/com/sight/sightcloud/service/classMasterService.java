package com.sight.sightcloud.service;

import com.sight.sightcloud.model.ClassMaster;
import com.sight.sightcloud.model.DataInstanceMaster;
import com.sight.sightcloud.model.UserStore;
import com.sight.sightcloud.repository.DataInstanceMasterRepository;
import com.sight.sightcloud.repository.classMasterRepository;
import com.sight.sightcloud.vo.DataInstanceMasterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

@Service
public class classMasterService {

    private final classMasterRepository classMasterRepository;
    private final DataInstanceMasterRepository dataInstanceMasterRepository;

    public classMasterService(classMasterRepository classMasterRepository, DataInstanceMasterRepository dataInstanceMasterRepository) {
        this.classMasterRepository = classMasterRepository;
        this.dataInstanceMasterRepository = dataInstanceMasterRepository;
    }

    public List<ClassMaster> findClassMasterByProjectStoreID(int projectStoreID){
        return classMasterRepository.findAllProjectStoreID(projectStoreID);
    }

    public List<DataInstanceMasterVO> findClassMasterByPinAndLastComment(int projectStoreID, int userStoreID){
        List<ClassMaster> pinnedClassMaster = classMasterRepository.findAllPinnedCMForProjectStoreIDAndUserStoreID(userStoreID,projectStoreID);
        List<ClassMaster> allClassMasterForprojectStoreID = classMasterRepository.findAllProjectStoreID(projectStoreID);

        List<DataInstanceMasterVO> responseForpinned = new ArrayList<>();
        List<DataInstanceMasterVO> responseForNonPinnned = new ArrayList<>();
        List<DataInstanceMasterVO> emptyDataInstance = new ArrayList<>();
        List<DataInstanceMasterVO> emptyCMPinned = new ArrayList<>();



        for(ClassMaster cm : allClassMasterForprojectStoreID){
            Optional<DataInstanceMaster> temp = dataInstanceMasterRepository.findDataInstanceByLastComment(cm.getItemMasterID());
            DataInstanceMasterVO dataInstanceMasterVO = new DataInstanceMasterVO();

            if(temp.isPresent()){
                dataInstanceMasterVO.setDataInstanceMaster(temp.get(),pinnedClassMaster.contains(cm));
                if(pinnedClassMaster.contains(cm)){
                    responseForpinned.add(dataInstanceMasterVO);
                }else{
                    responseForNonPinnned.add(dataInstanceMasterVO);
                }
            }else{
                UserStore emptyUser = new UserStore();
                emptyUser.setPhotoURL("empty");
                emptyUser.setUserName("empty");
                emptyUser.setLinkedPhone("empty");
                emptyUser.setLinkedEmail("empty");
                emptyUser.setDateViewPreference(0);
                emptyUser.setThemeID(0);
                emptyUser.setUserStoreID(0);
                emptyUser.setTimeViewPreference(0);
                dataInstanceMasterVO.setClassMaster(cm);
                dataInstanceMasterVO.setPinnedForCurrentUser(pinnedClassMaster.contains(cm));
                dataInstanceMasterVO.setUserStore(emptyUser);
                dataInstanceMasterVO.setDataInstanceID(999);
                dataInstanceMasterVO.setDataInstances("empty");
                dataInstanceMasterVO.setInstanceTime(Long.MAX_VALUE);
                dataInstanceMasterVO.setInstancesStatus(999);
                dataInstanceMasterVO.setDirectoryid(999);
                if(pinnedClassMaster.contains(cm)){
                    emptyCMPinned.add(dataInstanceMasterVO);
                }else{
                    emptyDataInstance.add(dataInstanceMasterVO);
                }
            }
        }

        Collections.sort(responseForpinned);
        Collections.sort(responseForNonPinnned);
        responseForpinned.addAll(emptyCMPinned);
        responseForpinned.addAll(responseForNonPinnned);
        responseForpinned.addAll(emptyDataInstance);

        return responseForpinned;
    }

    public ClassMaster findByItemMasterID(int ItemMasterID){
        return classMasterRepository.findByItemMasterID(ItemMasterID);
        //return classMasterRepository.findById(ItemMasterID).get();
    }

    public List<ClassMaster> getClassMaster(){
        return classMasterRepository.findAll();
    }

    public Boolean postClassMaster(ClassMaster classMaster){

        try {
            classMasterRepository.save(classMaster);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public Boolean deleteClassMaster(int itemMasterID){
        if(classMasterRepository.existsById(itemMasterID)){
            ClassMaster classMaster = classMasterRepository.findByItemMasterID(itemMasterID);
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



