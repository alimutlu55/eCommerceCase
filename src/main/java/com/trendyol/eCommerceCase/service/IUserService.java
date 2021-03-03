package com.trendyol.eCommerceCase.service;

import com.trendyol.eCommerceCase.exceptions.UserNameExistException;
import com.trendyol.eCommerceCase.model.User;


public interface IUserService {
    void signUp(User user) throws UserNameExistException;
    User findUserById(long id);
    User findUserByUsername(String username);
}
