package com.example.springbootdemo.controller;
import com.example.springbootdemo.DTO.ItemDto;
import com.example.springbootdemo.service.CountryService;
import com.example.springbootdemo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;
    private final CountryService countryService;

    @GetMapping
    public String listItems(Model model) {
        model.addAttribute("pageTitle", "Items catalog");
        model.addAttribute("items", itemService.getAllItems());
        return "items";
    }

    @GetMapping("/add")
    public String addItemForm(Model model) {
        model.addAttribute("pageTitle", "Add item");
        model.addAttribute("formTitle", "Add new item");
        model.addAttribute("formAction", "/items/add");
        model.addAttribute("item", new ItemDto());
        model.addAttribute("countries", countryService.getAllCountries());
        return "item-form";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute("item") ItemDto dto) {
        itemService.createItem(dto);
        return "redirect:/items";
    }

    @GetMapping("/edit/{id}")
    public String editItemForm(@PathVariable Long id, Model model) {
        ItemDto item = itemService.getItem(id);
        if (item == null) {
            return "redirect:/items";
        }
        model.addAttribute("pageTitle", "Edit item");
        model.addAttribute("formTitle", "Edit item");
        model.addAttribute("formAction", "/items/edit/" + id);
        model.addAttribute("item", item);
        model.addAttribute("countries", countryService.getAllCountries());
        return "item-form";
    }

    @PostMapping("/edit/{id}")
    public String editItem(@PathVariable Long id, @ModelAttribute("item") ItemDto dto) {
        itemService.updateItem(id, dto);
        return "redirect:/items";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return "redirect:/items";
    }
}
