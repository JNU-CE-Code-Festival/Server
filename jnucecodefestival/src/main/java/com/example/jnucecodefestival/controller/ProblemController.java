package com.example.jnucecodefestival.controller;

import net.minidev.json.JSONObject;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


@RestController
public class ProblemController {
    @Autowired
    private JdbcTemplate jdbc;

    @GetMapping(value="/problemList")
    public JSONObject getProblemListToJSON() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;

        JSONObject result = new JSONObject();
        List<Map<String, Object>> problemList = 
        this.jdbc.queryForList("select problem.Id,problemNum,problemTitle from problem,users where problem.grade=users.grade and users.username=\"" + userDetails.getUsername() + "\"");
        result.put("data", problemList);
        return result;
    }
}