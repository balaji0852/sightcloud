package com.sight.sightcloud.repository;

import com.sight.sightcloud.model.projectSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface projectSettingRepository extends JpaRepository<projectSetting,Integer> {

    @Query(value = "SELECT * FROM project_setting WHERE project_storeid= ?1",nativeQuery = true)
    projectSetting findByProjectStoreID(int projectStoreID);




}
