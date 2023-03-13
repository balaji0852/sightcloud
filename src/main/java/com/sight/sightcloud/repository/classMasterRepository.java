package com.sight.sightcloud.repository;

import com.sight.sightcloud.model.ClassMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface classMasterRepository  extends JpaRepository<ClassMaster,Integer>{

    //query classMaster by projectStoreID

    @Query(value = "SELECT * FROM classMaster   WHERE project_storeid =?1",nativeQuery = true)
    List<ClassMaster> findAllProjectStoreID(int projectStoreID);


    //@Query(value = "SELECT * FROM classMaster   WHERE project_storeid =?1",nativeQuery = true)
    ClassMaster findByItemMasterID(int ItemMasterID);

    @Query(value = "SELECT * FROM classMaster cm inner join pinnedclass pin\n" +
            "on pin.item_masterid=cm.item_masterid where pin.user_storeid=?1 AND pin.is_pinned=True " +
            "AND cm.project_storeid=?2",nativeQuery = true)
    List<ClassMaster> findAllPinnedCMForProjectStoreIDAndUserStoreID( int userStoreID,int projectStoreID);




}
