package com.sight.sightcloud.repository;

import com.sight.sightcloud.model.RegionStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface regionStoreRepository extends JpaRepository<RegionStore,Integer> {
}
