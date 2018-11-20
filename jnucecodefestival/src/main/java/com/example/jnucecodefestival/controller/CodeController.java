package com.example.jnucecodefestival.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CodeController {
    @Autowired
    private JdbcTemplate jdbctemplate;

    @GetMapping(value="/code")
    public String getCode(@RequestParam String number, Model model) {
        
        return "main";
    }
}