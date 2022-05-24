package com.sight.sightcloud.repository;

import com.sight.sightcloud.model.DataInstanceMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataInstanceMasterRepository extends JpaRepository<DataInstanceMaster,Integer> {
}
