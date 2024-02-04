package com.test.tyro;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.tyro.model.User;
import com.test.tyro.service.UserService;


@SpringBootTest
public class UserTests {

    @Autowired
    UserService service;

    @Test
    public void should_store_a_user(){
        User user = service.save(new User("Frank"));

        assertThat(user).hasFieldOrPropertyWithValue("name", "Frank");
    }
}
