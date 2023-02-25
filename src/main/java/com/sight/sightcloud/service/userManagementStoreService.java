package com.sight.sightcloud.service;

import com.sight.sightcloud.model.ProjectStore;
import com.sight.sightcloud.model.UserManagementStore;
import com.sight.sightcloud.model.UserStore;
import com.sight.sightcloud.repository.userManagementRepository;
import com.sight.sightcloud.repository.userStoreRepository;
import com.sight.sightcloud.repository.projectStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class userManagementStoreService {

    private final userManagementRepository userManagementRepository;

    private final userStoreRepository userStoreRepo;


    private final projectStoreRepository projectStoreRepository;

    public userManagementStoreService(userManagementRepository userManagementRepository, userStoreRepository userStoreRepo, projectStoreRepository projectStoreRepository) {
        this.userManagementRepository = userManagementRepository;
        this.userStoreRepo = userStoreRepo;
        this.projectStoreRepository = projectStoreRepository;
    }

    public boolean createProjectUser(UserManagementStore userManagementStore){
        try{
           userManagementRepository.save(userManagementStore);
           return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<UserManagementStore> getAllUserFromProject(int projectStoreID){

        return userManagementRepository.findAllUsersFromProject(projectStoreID);
    }


    public UserManagementStore updateUser(UserManagementStore userManagementStore){
        return userManagementRepository.save(userManagementStore);
    }


    public int addUser(String inviteeMail, int projectStoreID){
        UserStore userStore = userStoreRepo.findBylinkedEmail(inviteeMail);
        ProjectStore projectStore = projectStoreRepository.getById(projectStoreID);

        try {
            if (null != userStore) {
                Optional<UserManagementStore> userToManage = userManagementRepository.findByUserStore_UserStoreID(userStore.getUserStoreID());
                if (userToManage.isEmpty()) {
                    //create user in userManagement
                    //respond 200, user created
                    UserManagementStore userToCreate = new UserManagementStore();
                    userToCreate.setUserStore(userStore);
                    userToCreate.setProjectStore(projectStore);
                    userToCreate.setInvited(true);
                    userToCreate.setState(1);
                    //state 1-was present in platform invited for the project
                    return 200;
                } else {
                    //respond with 201, user exist
                    return 201;
                }
            } else {
                //create user and add to project or call addUser again
                UserStore creatingUserStore = new UserStore();
                creatingUserStore.setUserName("unknown");
                creatingUserStore.setDateViewPreference(0);
                creatingUserStore.setLinkedPhone("0");
                creatingUserStore.setLinkedEmail(inviteeMail);
                creatingUserStore.setPhotoURL(" ");
                creatingUserStore.setThemeID(0);
                creatingUserStore.setTimeViewPreference(0);
                userStoreRepo.save(creatingUserStore);
                UserStore checkForUserStore = userStoreRepo.findBylinkedEmail(inviteeMail);
                if(null!=checkForUserStore){
                    UserManagementStore userToCreate = new UserManagementStore();
                    userToCreate.setUserStore(userStore);
                    userToCreate.setProjectStore(projectStore);
                    userToCreate.setInvited(true);
                    //state - 2, was invited to the project, hence forth platform created a account,
                    //need user offical login for userStore updates
                    userToCreate.setState(2);
                    userStoreRepo.save(creatingUserStore);
                    return 200;
                }

            }
        }catch (Exception e){
            return 500;
        }

        return 500;
    }



}
