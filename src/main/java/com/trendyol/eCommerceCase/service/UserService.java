package com.trendyol.eCommerceCase.service;

import com.trendyol.eCommerceCase.exceptions.UsernameExistException;
import com.trendyol.eCommerceCase.model.User;


public interface UserService {
    User signUp(User user) throws UsernameExistException;
    User findUserById(long id);
    User findUserByUsername(String username);
}
