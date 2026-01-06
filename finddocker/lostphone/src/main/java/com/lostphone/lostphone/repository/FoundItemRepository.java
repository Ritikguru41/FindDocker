package com.lostphone.lostphone.repository;

import com.lostphone.lostphone.entity.FoundItem;
import com.lostphone.lostphone.entity.LostItem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoundItemRepository extends JpaRepository<FoundItem, Long> {

    
    void save(LostItem item);

    List<FoundItem> findByFinderUsername(String finderName);
}
