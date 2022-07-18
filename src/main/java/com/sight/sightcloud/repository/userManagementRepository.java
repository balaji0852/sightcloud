package com.sight.sightcloud.repository;

import com.sight.sightcloud.model.UserManagementStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface userManagementRepository extends JpaRepository<UserManagementStore,Integer> {


    Optional<UserManagementStore> findByUserStore_UserStoreID(int UserStoreID);
}
