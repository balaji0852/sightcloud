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
        ProjectStore projectStore = new ProjectStore();
        projectStore.setProjectStoreID(projectStoreID);

        try {
            if (null != userStore) {
                UserManagementStore userToManage = userManagementRepository.findByUserStoreID(userStore.getUserStoreID(),projectStoreID);
                if (null==userToManage) {
                    //create user in userManagement
                    //respond 200, user created
                    UserManagementStore userToCreate = new UserManagementStore();
                    userToCreate.setUserStore(userStore);
                    userToCreate.setProjectStore(projectStore);
                    userToCreate.setInvited(true);
                    userToCreate.setState(1);
                    //state 1-was present in platform invited for the project
                    userManagementRepository.save(userToCreate);
                    return 200;
                } else {
                    //user re enabling into project(case: user is in removed state in the project.
                    if(userToManage.getState()==4) {
                        //7/3/2023 - balaji :account will be reinitiated to project state=5(user reinitiated to project)
                        userToManage.setState(5);
                        userManagementRepository.save(userToManage);
                        return 202;
                    }
                    //respond with 201, user exist(duplicate)
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
                    userToCreate.setUserStore(checkForUserStore);
                    userToCreate.setProjectStore(projectStore);
                    userToCreate.setInvited(true);
                    //state - 2, was invited to the project, hence forth platform created a account,
                    //need user offical login for userStore updates
                    userToCreate.setState(2);
                    userManagementRepository.save(userToCreate);
                    return 200;
                }

            }
        }catch (Exception e){
            return 500;
        }

        return 500;
    }



}
