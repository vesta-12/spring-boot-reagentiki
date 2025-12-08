package com.example.springbootdemo.controller;

import com.example.springbootdemo.DTO.CountryDto;
import com.example.springbootdemo.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public String getCountry(@PathVariable Long id, Model model) {
        CountryDto dto = countryService.getCountryById(id);
        model.addAttribute("pageTitle", "Country details");
        model.addAttribute("country", dto);
        return "country-details";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("pageTitle", "Create country");
        model.addAttribute("country", new CountryDto());
        return "country-form";
    }

    @PostMapping("/create")
    public String createCountry(@ModelAttribute("country") CountryDto dto) {
        countryService.createCountry(dto);
        return "redirect:/countries";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        CountryDto dto = countryService.getCountryById(id);
        model.addAttribute("pageTitle", "Edit country");
        model.addAttribute("country", dto);
        return "country-form";
    }

    @PostMapping("/edit/{id}")
    public String updateCountry(@PathVariable Long id, @ModelAttribute("country") CountryDto dto) {
        countryService.updateCountry(id, dto);
        return "redirect:/countries";
    }

    @GetMapping("/delete/{id}")
    public String deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
        return "redirect:/countries";
    }
}
