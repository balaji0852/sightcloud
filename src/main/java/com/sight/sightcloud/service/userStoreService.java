package com.sight.sightcloud.service;


import com.sight.sightcloud.model.UserStore;
import com.sight.sightcloud.repository.userStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class userStoreService {

    @Autowired
    private  final userStoreRepository UserStoreRepository;

    public userStoreService(userStoreRepository userStoreRepository) {
        UserStoreRepository = userStoreRepository;
    }

    public UserStore findUserByEmail(String linkedEmail){
        return UserStoreRepository.findBylinkedEmail(linkedEmail);
    }

    public List<UserStore> findUserByPhone(String linkedPhone){
        return UserStoreRepository.findBylinkedPhone(linkedPhone);
    }

    public UserStore findUserByUserStoreID(int UserStoreID){
        return UserStoreRepository.findByuserStoreID(UserStoreID);
    }

    //balaji : Empty userStore will throw NPE
    public boolean findForDuplicateEmail(String linkingEmail){

            UserStore userStore = findUserByEmail(linkingEmail);
            return null==userStore;
                //return true;
           // }
        //}catch (NullPointerException e){
            //return true;
        //}
    }

    public boolean insertUser(UserStore userStore){

        try {
            UserStoreRepository.save(userStore);
            return true;
        }catch (Exception E){
            return false;
        }
    }

    public boolean deleteUser(int userStoreID){
        try {
            if(UserStoreRepository.existsById(userStoreID)){
                UserStore userStore = UserStoreRepository.getById(userStoreID);
                UserStoreRepository.delete(userStore);
                return true;
            }
        }catch (Exception E){
            return false;
        }
        return false;
    }

    public boolean updateUser(UserStore userStore){
        if(UserStoreRepository.existsById(userStore.getUserStoreID())){
            UserStoreRepository.save(userStore);
            return true;
        }
        return false;
    }
}
