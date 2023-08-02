package com.denomelchenko.shop.services;

import com.denomelchenko.shop.dao.UserItemDAO;
import com.denomelchenko.shop.models.Item;
import com.denomelchenko.shop.models.User;
import com.denomelchenko.shop.repositories.ItemRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserItemService {

    private final UserItemDAO userItemDAO;

    public UserItemService(UserItemDAO userItemDAO) {
        this.userItemDAO = userItemDAO;
    }

    @Transactional
    public void addItemToCart(Item item, User user) {
        Hibernate.initialize(user.getItems().add(item));
        Hibernate.initialize(item.getUsers().add(user));
    }

    public List<Item> getAllItemsInCart(User user) {
        return user.getItems();
    }

    @Transactional
    public void buyAllItemsFromCartUser(User user) {
        userItemDAO.deleteAllItemsFromCart(user);
    }
}
