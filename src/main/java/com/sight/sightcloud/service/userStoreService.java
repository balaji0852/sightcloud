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

    //balaji : 14/05/2023: setting default theme(dark:1) and view(1 day view)
    public boolean insertUser(UserStore userStore){

        try {
            UserStore _UserStore = UserStoreRepository.findBylinkedEmail(userStore.getLinkedEmail());
            if(null==_UserStore){
                userStore.setThemeID(1);
                userStore.setDateViewPreference(1);
                UserStoreRepository.save(userStore);
                return true;
            }
            return false;
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

    //balaji : 1/10/2023, modifying service to return a userStore
    //                    email - super key <- will be provided in userStore, provided fields will be updated...
    public UserStore updateUser(UserStore userStore){
        UserStore _userStore = UserStoreRepository.findBylinkedEmail(userStore.getLinkedEmail());

        userStore.setUserStoreID(_userStore.getUserStoreID());
        userStore.setThemeID(userStore.getThemeID()==-1? _userStore.getThemeID() :userStore.getThemeID());
        userStore.setDateViewPreference(userStore.getDateViewPreference()==-1?_userStore.getDateViewPreference():userStore.getDateViewPreference());
        userStore.setTimeViewPreference(userStore.getTimeViewPreference()==-1?_userStore.getTimeViewPreference():userStore.getTimeViewPreference());
        userStore.setUserName(userStore.getUserName().equals("empty")?_userStore.getUserName():userStore.getUserName());
        userStore.setLinkedPhone(userStore.getLinkedPhone().equals("empty")?_userStore.getLinkedPhone():userStore.getLinkedPhone());
        userStore.setPhotoURL(userStore.getPhotoURL().equals("empty")? _userStore.getPhotoURL() : userStore.getPhotoURL());

        try{
            UserStoreRepository.save(userStore);
        }catch (Exception e){
            //what to do?
        }

        return userStore;

    }
}
