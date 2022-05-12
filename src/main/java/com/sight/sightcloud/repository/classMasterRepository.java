package com.sight.sightcloud.repository;

import com.sight.sightcloud.model.ClassMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface classMasterRepository  extends JpaRepository<ClassMaster,Integer>{

}
