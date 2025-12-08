package com.example.springbootdemo.mapper;

import com.example.springbootdemo.DTO.ItemDto;
import com.example.springbootdemo.model.Country;
import com.example.springbootdemo.model.Item;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-04T23:01:46+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (JetBrains s.r.o.)"
)
@Component
public class ItemMapperImpl implements ItemMapper {

    @Override
    public ItemDto toDto(Item entity) {
        if ( entity == null ) {
            return null;
        }

        ItemDto itemDto = new ItemDto();

        itemDto.setManufacturerId( entityManufacturerId( entity ) );
        itemDto.setManufacturerName( entityManufacturerName( entity ) );
        itemDto.setId( entity.getId() );
        itemDto.setName( entity.getName() );
        itemDto.setPrice( entity.getPrice() );
        itemDto.setQuantity( entity.getQuantity() );

        return itemDto;
    }

    @Override
    public Item toEntity(ItemDto dto) {
        if ( dto == null ) {
            return null;
        }

        Item item = new Item();

        item.setManufacturer( itemDtoToCountry( dto ) );
        item.setId( dto.getId() );
        item.setName( dto.getName() );
        item.setPrice( dto.getPrice() );
        item.setQuantity( dto.getQuantity() );

        return item;
    }

    @Override
    public List<ItemDto> toDtoList(List<Item> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ItemDto> list = new ArrayList<ItemDto>( entities.size() );
        for ( Item item : entities ) {
            list.add( toDto( item ) );
        }

        return list;
    }

    private Long entityManufacturerId(Item item) {
        if ( item == null ) {
            return null;
        }
        Country manufacturer = item.getManufacturer();
        if ( manufacturer == null ) {
            return null;
        }
        Long id = manufacturer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityManufacturerName(Item item) {
        if ( item == null ) {
            return null;
        }
        Country manufacturer = item.getManufacturer();
        if ( manufacturer == null ) {
            return null;
        }
        String name = manufacturer.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected Country itemDtoToCountry(ItemDto itemDto) {
        if ( itemDto == null ) {
            return null;
        }

        Country country = new Country();

        country.setId( itemDto.getManufacturerId() );

        return country;
    }
}
