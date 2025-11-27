package com.example.springbootdemo.repository;

import com.example.springbootdemo.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}