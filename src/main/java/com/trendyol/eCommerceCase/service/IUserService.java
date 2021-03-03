package com.trendyol.eCommerceCase.service;

import com.trendyol.eCommerceCase.model.User;


public interface IUserService {
    void save(User user);
    User findUserById(long id);
}
