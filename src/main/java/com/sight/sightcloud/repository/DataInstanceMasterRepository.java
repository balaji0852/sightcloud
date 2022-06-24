package com.sight.sightcloud.repository;

import com.sight.sightcloud.model.DataInstanceMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataInstanceMasterRepository extends JpaRepository<DataInstanceMaster,Integer> {

//    @Query('SELECT * FROM DataInstancesMaster WHERE instancesTime <= :dateTimeEpoch AND instancesTime >= :zeroDateTimeEpoch AND itemMasterID = :itemMasterID')
//    Future<List<ClassDataInstanceMaterDuplicate>?> findDataInstanceByOneInterval(int dateTimeEpoch,int zeroDateTimeEpoch, int itemMasterID);
//
//    @Query('SELECT ClassMaster.itemClassColorID,DataInstancesMaster.dataInstanceID,DataInstancesMaster.itemMasterID,DataInstancesMaster.dataInstances,DataInstancesMaster.instancesTime  FROM DataInstancesMaster,ClassMaster WHERE DataInstancesMaster.itemMasterID=ClassMaster.itemMasterID AND instancesTime <= ?2 AND instancesTime >= ?1',
//    )
//    Future<List<ClassDataInstanceMaterDuplicate>?> findDataInstanceByIntervalWithClassMaster(int dateTimeEpoch,int zeroDateTimeEpoch);
//      @Query("SELECT u FROM User u WHERE u.status = ?1 and u.name = ?2")
//      User findUserByStatusAndName(Integer status, String name);
//
    @Query(value = "SELECT d FROM  DataInstanceMaster d WHERE d.instanceTime>=?1 and d.instanceTime<=?2 and d.classMaster.itemMasterID=?3 ")
    List<DataInstanceMaster> findDataInstanceByOneInterval(Long dateTimeEpoch,Long zeroDateTimeEpoch, int itemMasterID);

    @Query(value = "SELECT d FROM  DataInstanceMaster d WHERE d.instanceTime>=?1 and d.instanceTime<=?2")
    List<DataInstanceMaster> findDataInstanceByIntervalWithClassMaster(Long dateTimeEpoch,Long zeroDateTimeEpoch);

    @Query(value = "SELECT d FROM  DataInstanceMaster d WHERE d.instanceTime>=?1 and d.instanceTime<=?2 and d.classMaster.itemMasterID=?3  and d.instancesStatus=?4")
    List<DataInstanceMaster> findDataInstanceByOneIntervalV1(Long dateTimeEpoch,Long zeroDateTimeEpoch, int itemMasterID,int instancesStatus);

    @Query(value = "SELECT d FROM  DataInstanceMaster d WHERE d.instanceTime>=?1 and d.instanceTime<=?2 and d.instancesStatus=?3")
    List<DataInstanceMaster> findDataInstanceByIntervalWithClassMasterV1(Long dateTimeEpoch,Long zeroDateTimeEpoch,int instanceStatus);


}
