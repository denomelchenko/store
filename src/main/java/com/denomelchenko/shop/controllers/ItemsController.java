package com.denomelchenko.shop.controllers;

import com.denomelchenko.shop.security.UserDetailsImpl;
import com.denomelchenko.shop.services.ItemService;
import com.denomelchenko.shop.services.UserItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/items")
public class ItemsController {
    private final ItemService itemService;
    private final UserItemService userItemService;

    @Autowired
    public ItemsController(ItemService itemService, UserItemService userItemService) {
        this.itemService = itemService;
        this.userItemService = userItemService;
    }

    @GetMapping("/page/{page}")
    public String items(@PathVariable("page") int page, Model model,
                        @RequestParam(value = "sort-by", required = false) String sorting) {
        PageRequest pageRequest;
        if (sorting != null) {
            pageRequest = PageRequest.of(page, ItemService.ITEMS_PER_PAGE, Sort.by(sorting));
        } else {
            pageRequest = PageRequest.of(page, ItemService.ITEMS_PER_PAGE);

        }
        model.addAttribute("items", itemService.findAll(pageRequest));
        model.addAttribute("pageRequest", pageRequest);
        return "/items/index";
    }

    @GetMapping("/{id}")
    public String item(@PathVariable("id") int id, Model model) {
        model.addAttribute("item", itemService.getById(id));
        return "/items/show";
    }

    @PostMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable("id") int id) {
        userItemService.addItemToCart(itemService.getById(id),
                ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser());
        return "redirect:/items/{id}";
    }

    @PostMapping("/buy-all")
    public String buyAllItems() {
        userItemService.buyAllItemsFromCartUser(
                ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser()
        );
        return "redirect:/personal-info";
    }
}
