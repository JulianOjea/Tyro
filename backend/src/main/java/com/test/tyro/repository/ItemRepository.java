package com.test.tyro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.tyro.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{
    
}
