package com.example.springbootdemo.controller;

import com.example.springbootdemo.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/countries")
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    public String listCountries(Model model) {
        model.addAttribute("pageTitle", "Countries");
        model.addAttribute("countries", countryService.getAllCountries());
        return "countries";
    }
}