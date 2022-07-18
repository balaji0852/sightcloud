package com.sight.sightcloud.repository;

import com.sight.sightcloud.model.ProjectStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface projectStoreRepository extends JpaRepository<ProjectStore,Integer> {

//    @Query(value = "SELECT p FROM projectStore WHERE p.")

    Optional<ProjectStore> findByUserManagementStoreUserStoreID(int UserStoreID);

    //Optional<ProjectStore> findByUserManagementStore_UserStore(int userStoreID);

}
