package com.springStarter.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).get();
    }


    public void addUser(User User) {
        userRepository.save(User);
    }



    public void updateUser(String id, User User) {
        userRepository.save(User);
    }


    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }


}
