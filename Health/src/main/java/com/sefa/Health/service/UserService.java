package com.sefa.Health.service;

import com.sefa.Health.entity.User;
import com.sefa.Health.exception.NotFoundException;
import com.sefa.Health.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public User addUser(User user){

        return userRepository.save(user);
    }

    public void deleteUser(Long id){

        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User not found with id: " + id));

        userRepository.delete(user);

    }

    public List<User> getAllUsers(){

        List<User> users =  userRepository.findAll();
        return users;
    }


}
