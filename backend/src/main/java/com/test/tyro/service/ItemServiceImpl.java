package com.test.tyro.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.test.tyro.model.Item;
import com.test.tyro.model.User;
import com.test.tyro.repository.ItemRepository;
import com.test.tyro.repository.UserRepository;

@Service
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    private final UserRepository userRepository;

    public ItemServiceImpl(ItemRepository itemRepository, UserRepository userRepository){
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Item save(Item item, Long userId) {
        return userRepository.findById(userId).map(user -> { 
            item.setUser(user);
            return itemRepository.save(item);
        }).orElseThrow();
    }
    
}
