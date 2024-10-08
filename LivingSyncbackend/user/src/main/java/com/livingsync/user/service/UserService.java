package com.livingsync.user.service;

import com.livingsync.user.model.User;
import com.livingsync.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  SnsService snsService;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        String topicArn = "arn:aws:sns:ap-south-1:014498630957:notifications";
        snsService.subscribeEmailToTopic(topicArn,user.getEmail());
        snsService.subscribePhoneToTopic(topicArn,"+91"+user.getMobile());
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}