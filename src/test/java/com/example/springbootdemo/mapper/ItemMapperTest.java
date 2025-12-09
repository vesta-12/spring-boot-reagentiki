package com.example.springbootdemo.mapper;

import com.example.springbootdemo.DTO.ItemDto;
import com.example.springbootdemo.model.Country;
import com.example.springbootdemo.model.Item;
import com.example.springbootdemo.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class ItemMapperTest {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void toDtoTest() {
        Country manufacturer = new Country();
        manufacturer.setId(1L);
        manufacturer.setName("Sweden");
        manufacturer.setCode("SW");

        Item entity = new Item();
        entity.setName("Hydrochloric Acid");
        entity.setPrice(23600);
        entity.setQuantity(10);
        entity.setManufacturer(manufacturer);

        ItemDto itemDto = itemMapper.toDto(entity);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(itemDto),
                () -> Assertions.assertNotNull(itemDto.getName()),
                () -> Assertions.assertNotNull(itemDto.getManufacturerId()),
                () -> Assertions.assertNotNull(itemDto.getManufacturerName()),

                () -> Assertions.assertEquals(entity.getName(), itemDto.getName()),
                () -> Assertions.assertEquals(entity.getPrice(), itemDto.getPrice()),
                () -> Assertions.assertEquals(entity.getQuantity(), itemDto.getQuantity()),
                () -> Assertions.assertEquals(entity.getManufacturer().getId(), itemDto.getManufacturerId()),
                () -> Assertions.assertEquals(entity.getManufacturer().getName(), itemDto.getManufacturerName()),
                () -> Assertions.assertEquals(entity.getId(), itemDto.getId())
        );
    }

    @Test
    public void toEntityTest() {
        ItemDto itemDto = new ItemDto();
        itemDto.setName("Sodium Hydroxide");
        itemDto.setPrice(19900);
        itemDto.setQuantity(15);
        itemDto.setManufacturerId(2L);
        itemDto.setManufacturerName("Vietnam");

        Item entity = itemMapper.toEntity(itemDto);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(entity),
                () -> Assertions.assertNotNull(entity.getName()),
                () -> Assertions.assertNotNull(entity.getManufacturer()),

                () -> Assertions.assertEquals(itemDto.getName(), entity.getName()),
                () -> Assertions.assertEquals(itemDto.getPrice(), entity.getPrice()),
                () -> Assertions.assertEquals(itemDto.getQuantity(), entity.getQuantity()),
                () -> Assertions.assertEquals(itemDto.getManufacturerId(), entity.getManufacturer().getId()),
                () -> Assertions.assertEquals(itemDto.getId(), entity.getId())
        );
    }

    @Test
    public void toDtoListTest() {
        List<Item> entities = itemRepository.findAll();
        List<ItemDto> list = itemMapper.toDtoList(entities);

        for (int i = 0; i < list.size(); i++) {
            ItemDto itemDto = list.get(i);
            Item entity = entities.get(i);

            Assertions.assertAll(
                    () -> Assertions.assertNotNull(itemDto),
                    () -> Assertions.assertNotNull(itemDto.getName()),
                    () -> Assertions.assertNotNull(itemDto.getManufacturerId()),
                    () -> Assertions.assertNotNull(itemDto.getManufacturerName()),

                    () -> Assertions.assertEquals(entity.getName(), itemDto.getName()),
                    () -> Assertions.assertEquals(entity.getPrice(), itemDto.getPrice()),
                    () -> Assertions.assertEquals(entity.getQuantity(), itemDto.getQuantity()),
                    () -> Assertions.assertEquals(entity.getManufacturer().getId(), itemDto.getManufacturerId()),
                    () -> Assertions.assertEquals(entity.getManufacturer().getName(), itemDto.getManufacturerName()),
                    () -> Assertions.assertEquals(entity.getId(), itemDto.getId())
            );
        }
    }
}
