package com.test.tyro;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.tyro.model.User;
import com.test.tyro.service.UserService;


@SpringBootTest
public class UserTests {

    @Autowired
    UserService service;

    @Test void should_be_empty_database(){
        List<User> userList = service.findAll();

        assertThat(userList.size()).isEqualTo(0);
    }

    @Test 
    @DirtiesContext
    void should_find_all_users(){
        service.save(new User("Adam"));
        service.save(new User("Bob"));
        service.save(new User("Charlie"));

        List<User> userList = service.findAll();
        assertThat(userList.size()).isEqualTo(3);
        assertThat(userList.stream().anyMatch(user -> user.getName().equals("Adam"))).isEqualTo(true);
        assertThat(userList.stream().anyMatch(user -> user.getName().equals("Bob"))).isEqualTo(true);
        assertThat(userList.stream().anyMatch(user -> user.getName().equals("Charlie"))).isEqualTo(true);
        assertThat(userList.stream().anyMatch(user -> user.getName().equals("Denis"))).isEqualTo(false);
    }

    @Test
    @DirtiesContext
    public void should_store_a_user(){
        User user = service.save(new User("Denis"));

        assertThat(user).hasFieldOrPropertyWithValue("name", "Denis");
    }

    @Test
    public void should_not_find_by_id(){
        Optional<User> user = service.findById(1L);

        assertThat(user).isEmpty();
    }

    @Test
    @DirtiesContext
    public void should_find_by_id(){
        User user = service.save(new User("Adam"));
        Optional<User> foundUser = service.findById(user.getId());

        assertThat(foundUser.orElse(null)).isEqualTo(user);
    }

    @Test
    public void should_delete_user(){
        User user = service.save(new User("Adam"));
        Optional<User> deletedUser = service.delete(user);

        assertThat(service.findAll().size()).isEqualTo(0);
        assertThat(deletedUser).isEqualTo(deletedUser);
    }
}
