package com.example.springbootdemo.services;

import com.example.springbootdemo.DTO.ItemDto;
import com.example.springbootdemo.model.Country;
import com.example.springbootdemo.model.Item;
import com.example.springbootdemo.repository.CountryRepository;
import com.example.springbootdemo.repository.ItemRepository;
import com.example.springbootdemo.service.ItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CountryRepository countryRepository;

    @BeforeEach
    public void cleanDatabase() {
        itemRepository.deleteAll();
        countryRepository.deleteAll();
    }

    @Test
    public void getAllItemsTest() {
        Country country = new Country();
        country.setName("ListCountry");
        country.setCode("LI");
        Country savedCountry = countryRepository.save(country);

        Item item = new Item();
        item.setName("List Item");
        item.setPrice(1500);
        item.setQuantity(3);
        item.setManufacturer(savedCountry);
        itemRepository.save(item);
        List<ItemDto> items = itemService.getAllItems();

        Assertions.assertAll(
                () -> Assertions.assertNotNull(items),
                () -> Assertions.assertFalse(items.isEmpty()),
                () -> Assertions.assertEquals(1, items.size()),
                () -> Assertions.assertEquals("List Item", items.get(0).getName()),
                () -> Assertions.assertEquals(savedCountry.getId(), items.get(0).getManufacturerId())
        );
    }


    @Test
    public void getItemByIdTest() {
        Country country = new Country();
        country.setName("TestCountry");
        country.setCode("TST");
        Country savedCountry = countryRepository.save(country);

        Item item = new Item();
        item.setName("Test Item");
        item.setPrice(1000);
        item.setQuantity(5);
        item.setManufacturer(savedCountry);
        Item savedItem = itemRepository.save(item);

        ItemDto dto = itemService.getItem(savedItem.getId());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(dto),
                () -> Assertions.assertEquals(savedItem.getId(), dto.getId()),
                () -> Assertions.assertEquals(savedItem.getName(), dto.getName()),
                () -> Assertions.assertEquals(savedItem.getPrice(), dto.getPrice()),
                () -> Assertions.assertEquals(savedItem.getQuantity(), dto.getQuantity()),
                () -> Assertions.assertEquals(savedCountry.getId(), dto.getManufacturerId())
        );
    }

    @Test
    public void createItemTest() {
        Country country = new Country();
        country.setName("CreateCountry");
        country.setCode("CRT");
        Country savedCountry = countryRepository.save(country);

        ItemDto dto = new ItemDto();
        dto.setName("Created Item");
        dto.setPrice(2000);
        dto.setQuantity(10);
        dto.setManufacturerId(savedCountry.getId());

        ItemDto created = itemService.createItem(dto);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(created),
                () -> Assertions.assertNotNull(created.getId()),
                () -> Assertions.assertEquals(dto.getName(), created.getName()),
                () -> Assertions.assertEquals(dto.getPrice(), created.getPrice()),
                () -> Assertions.assertEquals(dto.getQuantity(), created.getQuantity()),
                () -> Assertions.assertEquals(savedCountry.getId(), created.getManufacturerId())
        );

        Item fromDb = itemRepository.findById(created.getId()).orElse(null);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(fromDb),
                () -> Assertions.assertEquals(created.getName(), fromDb.getName()),
                () -> Assertions.assertEquals(created.getPrice(), fromDb.getPrice()),
                () -> Assertions.assertEquals(created.getQuantity(), fromDb.getQuantity()),
                () -> Assertions.assertNotNull(fromDb.getManufacturer()),
                () -> Assertions.assertEquals(savedCountry.getId(), fromDb.getManufacturer().getId())
        );
    }

    @Test
    public void updateItemTest() {
        Country country1 = new Country();
        country1.setName("UpdateCountry1");
        country1.setCode("UC1");
        Country savedCountry1 = countryRepository.save(country1);

        Country country2 = new Country();
        country2.setName("UpdateCountry2");
        country2.setCode("UC2");
        Country savedCountry2 = countryRepository.save(country2);

        Item item = new Item();
        item.setName("Old Item");
        item.setPrice(3000);
        item.setQuantity(3);
        item.setManufacturer(savedCountry1);
        Item savedItem = itemRepository.save(item);

        ItemDto dto = new ItemDto();
        dto.setName("Updated Item");
        dto.setPrice(4000);
        dto.setQuantity(7);
        dto.setManufacturerId(savedCountry2.getId());

        ItemDto updated = itemService.updateItem(savedItem.getId(), dto);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(updated),
                () -> Assertions.assertEquals(savedItem.getId(), updated.getId()),
                () -> Assertions.assertEquals("Updated Item", updated.getName()),
                () -> Assertions.assertEquals(4000, updated.getPrice()),
                () -> Assertions.assertEquals(7, updated.getQuantity()),
                () -> Assertions.assertEquals(savedCountry2.getId(), updated.getManufacturerId())
        );

        Item fromDb = itemRepository.findById(savedItem.getId()).orElse(null);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(fromDb),
                () -> Assertions.assertEquals("Updated Item", fromDb.getName()),
                () -> Assertions.assertEquals(4000, fromDb.getPrice()),
                () -> Assertions.assertEquals(7, fromDb.getQuantity()),
                () -> Assertions.assertNotNull(fromDb.getManufacturer()),
                () -> Assertions.assertEquals(savedCountry2.getId(), fromDb.getManufacturer().getId())
        );
    }

    @Test
    public void deleteItemTest() {
        Country country = new Country();
        country.setName("DeleteCountry");
        country.setCode("DEL");
        Country savedCountry = countryRepository.save(country);

        Item item = new Item();
        item.setName("To Delete");
        item.setPrice(5000);
        item.setQuantity(2);
        item.setManufacturer(savedCountry);
        Item savedItem = itemRepository.save(item);

        itemService.deleteItem(savedItem.getId());

        Assertions.assertTrue(itemRepository.findById(savedItem.getId()).isEmpty());
    }

    @Test
    public void manyToOneRelationTest() {
        Country country = new Country();
        country.setName("RelationCountry");
        country.setCode("REL");
        Country savedCountry = countryRepository.save(country);

        ItemDto dto = new ItemDto();
        dto.setName("Relation Item");
        dto.setPrice(6000);
        dto.setQuantity(9);
        dto.setManufacturerId(savedCountry.getId());

        ItemDto created = itemService.createItem(dto);

        Item fromDb = itemRepository.findById(created.getId()).orElse(null);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(created),
                () -> Assertions.assertNotNull(fromDb),
                () -> Assertions.assertNotNull(fromDb.getManufacturer()),
                () -> Assertions.assertEquals(savedCountry.getId(), fromDb.getManufacturer().getId()),
                () -> Assertions.assertEquals(savedCountry.getId(), created.getManufacturerId())
        );
    }
}
