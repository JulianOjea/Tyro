package com.test.tyro;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.test.tyro.model.Item;
import com.test.tyro.model.User;
import com.test.tyro.service.ItemService;
import com.test.tyro.service.UserService;

@SpringBootTest
public class ItemTests {
    
    @Autowired
    ItemService itemService;

    @Autowired
    UserService userService;

    @Test
    @DirtiesContext
    public void should_store_an_item_on_userId(){
        User user = userService.save(new User("Adam"));

        LocalDate now = LocalDate.now();
        Item item = itemService.save(new Item(5.0, "12AB", now), user.getId());

        assertThat(item.getUser().getId()).isEqualTo(user.getId());
        assertThat(item.getCreation_date()).isEqualTo(now);
        assertThat(item.getApi_ID()).isEqualTo("12AB");
        assertThat(item.getRating()).isEqualTo(5.0);
    }

    @Test
    @DirtiesContext
    public void should_find_by_user_id(){
        User user = userService.save(new User("Adam"));
        User user2 = userService.save(new User("Bob"));

        LocalDate now = LocalDate.now();
        Item item = itemService.save(new Item(5.0, "12AB", now), user.getId());
        Item item2 = itemService.save(new Item(10.0, "24CD", now), user.getId());
        
        Item item3 = itemService.save(new Item(10.0, "34EF", now), user2.getId());

        List<Item> userItemList = itemService.findByUserId(user.getId());

        List<String> userApiIdList = userItemList.stream().map(Item::getApi_ID).collect(Collectors.toList());
        
        assertThat(userItemList.size()).isEqualTo(2);

        assertThat(userApiIdList.contains(item.getApi_ID())).isEqualTo(true);
        assertThat(userApiIdList.contains(item2.getApi_ID())).isEqualTo(true);
        assertThat(userApiIdList.contains(item3.getApi_ID())).isEqualTo(false);

        assertThat(userItemList.stream().anyMatch(i -> item.getUser().getId().equals(user.getId()))).isEqualTo(true);
        assertThat(userItemList.stream().anyMatch(i -> item2.getUser().getId().equals(user.getId()))).isEqualTo(true);
    }

    @Test
    @DirtiesContext
    public void should_not_find_items_by_user_id(){
        User user = userService.save(new User("Adam"));

        List<Item> userItemList = itemService.findByUserId(user.getId());

        assertThat(userItemList.size()).isEqualTo(0);
    }

    @Test
    @DirtiesContext
    public void should_find_item_by_item_id(){
        User user = userService.save(new User("Adam"));

        LocalDate now = LocalDate.now();
        Item savedItem = itemService.save(new Item(5.0, "12AB", now), user.getId());
        itemService.save(new Item(10.0, "34CD", now), user.getId());

        Item item = itemService.findById(savedItem.getId()).get();

        assertThat(item.getId()).isEqualTo(savedItem.getId());
        ///assertThat(item.getUser()).isEqualTo(savedItem.getUser()); TODO: find item by user id
    }

    @Test
    @DirtiesContext
    public void should_find_not_find_item_by_item_id(){
        userService.save(new User("Adam"));

        assertThrows(
            NoSuchElementException.class, 
            () -> {
                itemService.findById(1L).get();
            });
    }

    
    /**
     *  Should only update rating
     */
    @Test
    @DirtiesContext
    public void should_update_item(){
        User user = userService.save(new User("Adam"));

        LocalDate now = LocalDate.now();
        Item savedItem = itemService.save(new Item(5.0, "12AB", now), user.getId());
        savedItem.setRating(10.0);
        itemService.update(savedItem);

        Item foundItem = itemService.findById(savedItem.getId()).get();
        assertThat(foundItem.getRating()).isEqualTo(10.0);
        assertThat(foundItem.getApi_ID()).isEqualTo("12AB");
        assertThat(foundItem.getId()).isEqualTo(savedItem.getId());
        //assertThat(foundItem.getUser()).isEqualTo(savedItem.getUser()); TODO: find item by user id
        assertThat(foundItem.getCreation_date()).isEqualTo(savedItem.getCreation_date());
    }

    //TODO: public void should not update item

    @Test
    @DirtiesContext
    public void should_delete_item(){
        User user = userService.save(new User("Adam"));

        LocalDate now = LocalDate.now();
        Item savedItem = itemService.save(new Item(5.0, "12AB", now), user.getId());
        itemService.delete(savedItem);

        List<Item> itemList = itemService.findByUserId(user.getId());

        assertThat(itemList.size()).isEqualTo(0);
    }

    //TODO: public void should not delete
}
