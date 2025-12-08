package com.example.springbootdemo.service;

import com.example.springbootdemo.DTO.ItemDto;
import com.example.springbootdemo.mapper.ItemMapper;
import com.example.springbootdemo.model.Country;
import com.example.springbootdemo.model.Item;
import com.example.springbootdemo.repository.CountryRepository;
import com.example.springbootdemo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final CountryRepository countryRepository;
    private final ItemMapper itemMapper;

    @Transactional(readOnly = true)
    public List<ItemDto> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return itemMapper.toDtoList(items);
    }

    @Transactional(readOnly = true)
    public ItemDto getItem(Long id) {
        Item item = itemRepository.findById(id).orElse(null);
        return item != null ? itemMapper.toDto(item) : null;
    }

    public ItemDto createItem(ItemDto dto) {
        Country manufacturer = countryRepository.findById(dto.getManufacturerId()).orElse(null);
        if (manufacturer == null) {
            return null;
        }

        Item item = new Item();
        item.setName(dto.getName());
        item.setPrice(dto.getPrice());
        item.setQuantity(dto.getQuantity());
        item.setManufacturer(manufacturer);

        Item saved = itemRepository.save(item);
        return itemMapper.toDto(saved);
    }

    public ItemDto updateItem(Long id, ItemDto dto) {
        Item existing = itemRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }

        Country manufacturer = countryRepository.findById(dto.getManufacturerId()).orElse(null);
        if (manufacturer == null) {
            return null;
        }

        existing.setName(dto.getName());
        existing.setPrice(dto.getPrice());
        existing.setQuantity(dto.getQuantity());
        existing.setManufacturer(manufacturer);

        Item saved = itemRepository.save(existing);
        return itemMapper.toDto(saved);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}