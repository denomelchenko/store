package com.denomelchenko.shop.services;

import com.denomelchenko.shop.models.Item;
import com.denomelchenko.shop.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public Item getById(int id) {
        return itemRepository.findById(id).orElse(null);
    }
}
