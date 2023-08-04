package com.denomelchenko.shop.services;

import com.denomelchenko.shop.models.Item;
import com.denomelchenko.shop.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    public static final int ITEMS_PER_PAGE = 15;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll(PageRequest pageRequest) {
        return itemRepository.findAll(pageRequest).getContent();
    }

    public Item getById(int id) {
        return itemRepository.findById(id).orElse(null);
    }
}
