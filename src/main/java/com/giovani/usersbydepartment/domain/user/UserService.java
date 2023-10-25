package com.giovani.usersbydepartment.domain.user;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
   public Page<User> findAll(Pageable pageable){
        return userRepository.findAll(pageable);
   }
}
