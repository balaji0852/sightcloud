package com.sight.sightcloud.repository;

import com.sight.sightcloud.model.ProjectStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface projectStoreRepository extends JpaRepository<ProjectStore,Integer> {

//    @Query(value = "SELECT p FROM projectStore WHERE p.")

    @Query(value = "SELECT * from project_store p INNER JOIN user_management_store u on u.project_" +
            "storeid=p.project_storeid AND u.user_storeid =?1 ",nativeQuery = true)
    List<ProjectStore> findAllByUserManagementStore_UserStoreID(int UserStoreID);

    @Query(value = "SELECT * from project_store p WHERE p.project_storeid =?1",nativeQuery = true)
    ProjectStore findByprojectStoreID( int projectStoreID);

}
