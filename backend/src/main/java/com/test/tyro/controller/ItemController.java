package com.test.tyro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.tyro.model.Item;
import com.test.tyro.service.ItemService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @PostMapping("/users/{userId}/items")
    public ResponseEntity<?> postMethodName(@PathVariable(value = "userId") Long userId, @RequestBody Item itemRequest) {
        if(userId == null){
			return ResponseEntity.badRequest().body("Id is required in path");
		}
        if(itemRequest.getApi_ID() == null || itemRequest.getApi_ID().isEmpty()){
			return ResponseEntity.badRequest().body("API Id is required");
		}
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.save(itemRequest, userId));
    }
}
