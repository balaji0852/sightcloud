package com.sight.sightcloud.repository;

import com.sight.sightcloud.model.ClassMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface classMasterRepository  extends JpaRepository<ClassMaster,Integer>{

    //query classMaster by projectStoreID
    @Query(value = "select c from classMaster WHERE c.projectStore.projectStoreID =? ")
    Optional<ClassMaster> findByProjectStoreID(int projectStoreID);

}
