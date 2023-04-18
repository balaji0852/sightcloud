package com.sight.sightcloud.repository;

import com.sight.sightcloud.model.pinnedClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface directoryRepository extends JpaRepository<pinnedClass,Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM pinnedclass p WHERE p.item_masterid=?2 and p.user_storeid=?1 ")
    pinnedClass findPin(int userStoreId,int itemMasterID);
}
