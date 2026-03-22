package com.webgram.service;

import com.webgram.model.Contact;
import com.webgram.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    User create (User user);
}
