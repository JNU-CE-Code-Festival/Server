package com.example.jnucecodefestival.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;

@RestController
public class RankController {

    @Autowired
    private JdbcTemplate jdbctemplate;

    @GetMapping(value="/rank")
    public JSONObject getRank() throws DataAccessException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;

        List<Map<String, Object>> rankData = 
        jdbctemplate
            .queryForList("select username, count(*) as totalScore from solve group by username order by totalScore desc");
        List<Map<String, Object>> list = 
        jdbctemplate
            .queryForList("select userName, problemNum, submitCount, language, score from solve order by problemNum");
        System.out.println(list.toString());
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("rank", rankData);
        jsonobject.put("data", list);
        return jsonobject;
    }
}