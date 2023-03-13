package com.sight.sightcloud.repository;

import com.sight.sightcloud.model.pinnedClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface directoryRepository extends JpaRepository<pinnedClass,Integer> {
}
