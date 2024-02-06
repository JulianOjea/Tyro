package com.test.tyro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.tyro.model.Item;
import com.test.tyro.service.ItemService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

//TODO: improve return exceptions
@RestController
@RequestMapping("/api")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @PostMapping("/users/{userId}/items")
    public ResponseEntity<?> createItem(@PathVariable(value = "userId") Long userId, @RequestBody Item itemRequest) {
        if(userId == null){
			return ResponseEntity.badRequest().body("Id is required in path");
		}
        if(itemRequest.getApi_ID() == null || itemRequest.getApi_ID().isEmpty()){
			return ResponseEntity.badRequest().body("API Id is required");
		}
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.save(itemRequest, userId));
    }

    @GetMapping("/users/{userId}/items")
    public ResponseEntity<List<Item>> getAllItemsByUserId(@PathVariable(value = "userId") Long userId) {

        List<Item> items = itemService.findByUserId(userId);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") long itemId) {
        Item item = itemService.findById(itemId).get();
        itemService.delete(item);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // @PutMapping("/comments/{id}")
    // public ResponseEntity<Item> updateItem(@PathVariable("id") long id, @RequestBody Item itemRequest) {
    //     Item item = itemService.findById(id).get();

    //     item.setContent(itemRequest.);

    //     return new ResponseEntity<>(commentRepository.save(comment), HttpStatus.OK);
    // }
}   
