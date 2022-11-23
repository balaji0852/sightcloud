package com.sight.sightcloud.repository;

import com.sight.sightcloud.model.ServicePlanStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ServiceStoreRepository extends JpaRepository<ServicePlanStore,Integer> {
}
