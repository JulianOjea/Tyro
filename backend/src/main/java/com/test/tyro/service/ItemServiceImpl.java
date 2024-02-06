package com.test.tyro.service;

import java.util.List;
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
            Item newItem = new Item(item, user);
            return itemRepository.save(newItem);
        }).orElseThrow();
    }

    @Override
    public List<Item> findByUserId(Long userID) {
        return itemRepository.findByUserId(userID);
    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public Optional<Item> delete(Item item) {
        Optional<Item> itemOP = itemRepository.findById(item.getId());

        itemOP.ifPresent(u->{
            itemRepository.delete(item);
        });
        return itemOP;

    }

    //Maybe id should come as path variable? !!
    @Override
    public Optional<Item> update(Item item) {
        Optional<Item> itemToUpdate = itemRepository.findById(item.getId());

        if (itemToUpdate.isPresent()){
            itemToUpdate.get().setRating(item.getRating());
            return Optional.of(itemRepository.save(itemToUpdate.get()));
        }
        return itemToUpdate;
    }
    
}
