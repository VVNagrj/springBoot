package com.springStarter.mongoDB.bookstore.User;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {


	public List<User> getUsers();

    public User getUserById(String id);


    public void addUser(User User);


    public void updateUser(String id, User User);


    public void deleteUser(String id);


}
