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


    Optional<UserManagementStore> findByUserStore_UserStoreID(int UserStoreID);

    @Query(value = "SELECT * from  user_management_store where project_storeid = ?1",nativeQuery = true)
    List<UserManagementStore> findAllUsersFromProject(int projectStoreID);



}
