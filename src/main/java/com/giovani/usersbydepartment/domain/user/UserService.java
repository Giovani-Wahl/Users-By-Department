package com.giovani.usersbydepartment.domain.user;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
   public User save(User user){
        return userRepository.save(user);
   }
   @Transactional
   public void delete(User user){
        userRepository.delete(user);
   }
   public Page<User> findAll(Pageable pageable){
        return userRepository.findAll(pageable);
   }
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }
    public boolean existsUserByName(String name){
        return userRepository.existsUserByName(name);
    }
}
