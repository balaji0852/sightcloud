package com.sight.sightcloud.service;


import com.sight.sightcloud.model.pinnedClass;
import com.sight.sightcloud.repository.directoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class directoryService {

    private final directoryRepository directoryRepository;


    @Autowired
    public directoryService(directoryRepository directoryRepository) {
        this.directoryRepository = directoryRepository;
    }


    public boolean updateDirectory(pinnedClass pinnedClass){
        try{
            pinnedClass pin = directoryRepository.findPin(pinnedClass.getUserStore().getUserStoreID(),pinnedClass.getClassMaster().getItemMasterID());
            if(null==pin && pinnedClass.isPinned()){
                directoryRepository.save(pinnedClass);
            }else{
                directoryRepository.delete(pin);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<pinnedClass> getAllDirectories(){
        return directoryRepository.findAll();
    }
}
