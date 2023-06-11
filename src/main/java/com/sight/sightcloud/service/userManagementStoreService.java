package com.sight.sightcloud.service;

import com.sight.sightcloud.model.*;
import com.sight.sightcloud.repository.classMasterRepository;
import com.sight.sightcloud.repository.userManagementRepository;
import com.sight.sightcloud.repository.userStoreRepository;
import com.sight.sightcloud.repository.projectStoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userManagementStoreService {

    private final userManagementRepository userManagementRepository;

    private final userStoreRepository userStoreRepo;


    private final projectStoreRepository projectStoreRepository;

    private final classMasterRepository classMasterRepository;

    private final directoryService directoryService;

    public userManagementStoreService(com.sight.sightcloud.repository.userManagementRepository userManagementRepository, userStoreRepository userStoreRepo, com.sight.sightcloud.repository.projectStoreRepository projectStoreRepository, com.sight.sightcloud.repository.classMasterRepository classMasterRepository, com.sight.sightcloud.service.directoryService directoryService) {
        this.userManagementRepository = userManagementRepository;
        this.userStoreRepo = userStoreRepo;
        this.projectStoreRepository = projectStoreRepository;
        this.classMasterRepository = classMasterRepository;
        this.directoryService = directoryService;
    }


    public boolean createProjectUser(UserManagementStore userManagementStore){
        try{
           userManagementRepository.save(userManagementStore);
           createSelfChat(userManagementStore.getUserStore(),userManagementStore.getProjectStore());
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

                    //Balaji:10/06/2023: adding below feature
                    createSelfChat(userStore,projectStore);
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
                creatingUserStore.setDateViewPreference(1);//Balaji : 11/6/2023 : default setting
                creatingUserStore.setLinkedPhone("0");
                creatingUserStore.setLinkedEmail(inviteeMail);
                creatingUserStore.setPhotoURL(" ");
                creatingUserStore.setThemeID(1);//Balaji : 11/6/2023 : default setting
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

                    //Balaji:10/06/2023: adding below feature
                    createSelfChat(checkForUserStore,projectStore);

                    return 200;
                }

            }
        }catch (Exception e){
            return 500;
        }

        return 500;
    }



    public void createSelfChat(UserStore userStore,ProjectStore projectStore){
        //Balaji : 10/6/2023 : self char development
        UserStore userStore1 = userStoreRepo.findByuserStoreID(userStore.getUserStoreID());
        ClassMaster classMaster = new ClassMaster().createSelfChatClassMaster(userStore1,projectStore);
        classMaster =  classMasterRepository.save(classMaster);


        pinnedClass pinned = new pinnedClass();
        pinned.setPinned(true);
        pinned.setUserStore(userStore1);
        pinned.setClassMaster(classMaster);
        pinned.setFolderID(2);
        boolean gotPinned = directoryService.updateDirectory(pinned);
        System.out.println(" added user self chat id "+classMaster.getItemMasterID()+" did item pin?"+gotPinned);
    }



}
