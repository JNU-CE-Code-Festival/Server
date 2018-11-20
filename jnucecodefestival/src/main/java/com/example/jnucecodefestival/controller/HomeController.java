package com.example.jnucecodefestival.controller;

import java.util.List;
import java.util.Map;

import org.hibernate.annotations.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;


@Controller
public class HomeController {
    @Autowired
    private JdbcTemplate jdbc;

    @GetMapping(value="/")
    public String getProblemList(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;

        List<Map<String, Object>> list = this.jdbc.queryForList("select problem.Id,problemNum,problemTitle from problem,users where problem.grade=users.grade and users.username=\"" + userDetails.getUsername() + "\"");
        System.out.println("현재 아이디는" + userDetails.getUsername());
        list.forEach(System.out::println);
        model.addAttribute("problemList", list);
        return "hello";
    }

    
    
}