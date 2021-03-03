package com.trendyol.eCommerceCase.service;

import com.trendyol.eCommerceCase.dao.IUserRepository;
import com.trendyol.eCommerceCase.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public void save(User user) {
        iUserRepository.save(user);
    }

    @Override
    public User findUserById(long id) {
       return  iUserRepository.findById(id).get();
    }
}
