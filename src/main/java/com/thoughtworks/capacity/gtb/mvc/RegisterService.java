package com.thoughtworks.capacity.gtb.mvc;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterService {
    private Map<Integer, User> userMap = new HashMap<>();

    public RegisterService() {
        userMap.put(1, new User(1, "Bob123", "bob_123", "bob@test.com"));
        userMap.put(2, new User(2, "Ann123", "ann_123", "ann@test.com"));
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    public void createUser(User user) {
        user.setId(userMap.size() + 1);
        userMap.put(user.getId(), user);
    }

    public User getUserById(Integer id) {
        return userMap.get(id);
    }

    public Boolean checkExistUsername(String registerName){
        for(User user: userMap.values()){
            if(user.getUsername().equals(registerName)){
                return true;
            }
        }

        return false;
    }

    public User getUser(String username, String password){
        for(User user: userMap.values()){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
}
