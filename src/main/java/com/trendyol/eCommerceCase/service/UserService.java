package com.trendyol.eCommerceCase.service;

import com.trendyol.eCommerceCase.dao.IUserRepository;
import com.trendyol.eCommerceCase.exceptions.UserNameExistException;
import com.trendyol.eCommerceCase.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.trendyol.eCommerceCase.constants.Constants.*;

@Service
public class UserService implements IUserService {

    private IUserRepository iUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(IUserRepository iUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.iUserRepository = iUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User signUp(User user) throws UserNameExistException {
        User fetchedUser = findUserByUsername(user.getUsername());
        if(fetchedUser != null) throw new UserNameExistException(USERNAME_EXIST);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        iUserRepository.save(user);
        return user;
    }

    @Override
    public User findUserById(long id) {
       return  iUserRepository.findById(id).get();
    }

    @Override
    public User findUserByUsername(String username) {
        return iUserRepository.findByUsername(username);
    }
}
