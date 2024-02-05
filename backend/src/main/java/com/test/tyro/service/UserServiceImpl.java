package com.test.tyro.service;

import com.test.tyro.model.User;
import com.test.tyro.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public User save (User user){
        return userRepository.save(user);
    }

    @Override
    public Optional<User> delete(User user) {
        Optional<User> userOp = userRepository.findById(user.getId());
        userOp.ifPresent(u -> {
            userRepository.delete(user);
        });
        return userOp;
    }
}
