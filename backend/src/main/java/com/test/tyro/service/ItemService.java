package com.test.tyro.service;

import java.util.List;
import java.util.Optional;

import com.test.tyro.model.Item;


public interface ItemService {
    Item save(Item item, Long userId);
    Optional<Item> findById(Long id);
    List<Item> findByUserId(Long userID);
    //TODO: FIND ALL ITEMS ?
    Optional<Item> delete(Item item);
    Optional<Item> update(Item item);
}
