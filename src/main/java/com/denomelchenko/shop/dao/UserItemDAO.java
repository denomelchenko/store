package com.denomelchenko.shop.dao;

import com.denomelchenko.shop.models.User;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Component
public class UserItemDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserItemDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void deleteAllItemsFromCart(User user) {
        jdbcTemplate.update("DELETE FROM User_Item WHERE user_id = ?", user.getId());
        user.getItems().clear();
    }
}
