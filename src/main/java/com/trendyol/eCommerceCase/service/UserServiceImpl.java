package com.trendyol.eCommerceCase.service;

import com.trendyol.eCommerceCase.dao.UserRepository;
import com.trendyol.eCommerceCase.exceptions.UsernameExistException;
import com.trendyol.eCommerceCase.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.trendyol.eCommerceCase.constants.Constants.*;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User signUp(User user) throws UsernameExistException {
        User fetchedUser = findUserByUsername(user.getUsername());
        if(fetchedUser != null) throw new UsernameExistException(USERNAME_EXIST);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public User findUserById(long id) {
       return  userRepository.findById(id).get();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
