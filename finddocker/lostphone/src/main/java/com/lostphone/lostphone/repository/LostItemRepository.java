package com.lostphone.lostphone.repository;

import com.lostphone.lostphone.entity.LostItem;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LostItemRepository extends JpaRepository<LostItem, Long> {

    //Optional<LostItem> findByImei(String imei);
    List<LostItem> findByImei(String imei);


    Object findByMatchedTrue();
}
