package com.test.tyro.service;

import com.test.tyro.model.Item;

public interface ItemService {
    Item save(Item item, Long userId);
}
