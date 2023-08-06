package com.denomelchenko.shop.services;

import com.denomelchenko.shop.models.Item;
import com.denomelchenko.shop.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    public static final int ITEMS_PER_PAGE = 15;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAllByPageRequest(PageRequest pageRequest) {
        return itemRepository.findAll(pageRequest).getContent();
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item getById(int id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteById(int id) {
        itemRepository.deleteById(id);
    }

    public void addItem(Item item) {
        itemRepository.save(item);
    }

    public void update(Item item, int id) {
        item.setId(id);
        itemRepository.save(item);
    }
}
