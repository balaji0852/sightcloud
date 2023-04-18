package com.sight.sightcloud.repository;

import com.sight.sightcloud.model.UserStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface userStoreRepository extends JpaRepository<UserStore,Integer> {


    @Query(value = "SELECT * FROM user_store WHERE LOWER(linked_email) = ?1",nativeQuery = true)
    UserStore findBylinkedEmail(String userEmail);

    List<UserStore> findBylinkedPhone(String userPhone);

    UserStore findByuserStoreID(int userStoreID);


}
