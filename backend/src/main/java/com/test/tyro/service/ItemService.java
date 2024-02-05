package com.test.tyro.service;

import java.util.List;

import com.test.tyro.model.Item;

public interface ItemService {
    Item save(Item item, Long userId);
    List<Item> findByUserId(Long userID);
}
