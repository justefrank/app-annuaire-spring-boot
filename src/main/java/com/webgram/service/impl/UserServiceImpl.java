package com.webgram.service.impl;

import com.webgram.model.User;
import com.webgram.repository.UserRepository;
import com.webgram.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    //Constructeur pour injecter le repository dans le service
    public UserServiceImpl(UserRepository repo)
    {
        this.repo = repo;
    }


    @Override
    public List<User> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public User create(User user) {
        // verifier si email existe deja pour eviter les doublons
        if(repo.existsByEmail(user.getEmail())){
            throw new IllegalArgumentException("Email existe deja");
        }
        return repo.save(user);
    }
}
