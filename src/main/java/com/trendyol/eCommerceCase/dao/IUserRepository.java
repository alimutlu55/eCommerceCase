package com.trendyol.eCommerceCase.dao;

import com.trendyol.eCommerceCase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
