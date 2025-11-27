package com.example.springbootdemo.mapper;

import com.example.springbootdemo.DTO.ItemDto;
import com.example.springbootdemo.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(source = "manufacturer.id",   target = "manufacturerId")
    @Mapping(source = "manufacturer.name", target = "manufacturerName")
    ItemDto toDto(Item entity);

    @Mapping(source = "manufacturerId", target = "manufacturer.id")
    Item toEntity(ItemDto dto);

    List<ItemDto> toDtoList(List<Item> entities);
}