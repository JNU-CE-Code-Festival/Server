package com.example.jnucecodefestival.controller;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
public class ProblemController {
    @Autowired
    private JdbcTemplate jdbc;

    @GetMapping(value="/problemList")
    public JSONObject getProblemListToJSON() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;

        JSONObject result = new JSONObject();
        List<Map<String, Object>> problemList = this.jdbc.queryForList("select problem.Id,problemNum,problemTitle from problem,users where problem.grade=users.grade and users.username=\"" + userDetails.getUsername() + "\"");
        result.put("data", problemList);
        return result;
    }

    private JSONArray makeJSONArray(List<Map<String, Object>> list) {
        JSONArray jsonArr = new JSONArray();
        for (Map<String, Object> map : list) {
            JSONObject object = new JSONObject();
            for(Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                try {
                    object.put(key, value);
                } catch (Exception e) {
                
                    System.out.println("검출");
                
                }
            }
            jsonArr.add(object);
        }

        System.out.println(jsonArr.toJSONString());
        return jsonArr;

    }
}