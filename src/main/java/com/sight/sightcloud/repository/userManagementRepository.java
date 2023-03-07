package com.sight.sightcloud.repository;

import com.sight.sightcloud.model.UserManagementStore;
import com.sight.sightcloud.model.UserStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface userManagementRepository extends JpaRepository<UserManagementStore,Integer> {


    @Query(value = "SELECT * FROM  user_management_store u WHERE u.project_storeid=?2 and u.user_storeid=?1",nativeQuery = true)
    UserManagementStore findByUserStoreID(int UserStoreID,int projectStoreID);


    //7/3/2023 : balaji : avoid deleted users in the project -->and state!=4
    @Query(value = "SELECT * from  user_management_store where project_storeid = ?1 and state!=4",nativeQuery = true)
    List<UserManagementStore> findAllUsersFromProject(int projectStoreID);




}
