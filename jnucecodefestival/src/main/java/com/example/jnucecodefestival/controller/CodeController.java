package com.example.jnucecodefestival.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CodeController {
    @Autowired
    private JdbcTemplate jdbctemplate;

    @GetMapping(value="/code")
    public String getCode(@RequestParam String number, Model model) throws DataAccessException{
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;

        Map<String, Object> problem = this.jdbctemplate.queryForMap("select * from problem where id=" + number );
        Map<String, Object> grade = this.jdbctemplate.queryForMap("select grade from users where username=\"" + userDetails.getUsername() + "\"");
        System.out.println(grade.get("grade"));
        System.out.println(problem.get("grade"));
        if(grade.get("grade").toString().equals(problem.get("grade").toString())) {
            model.addAttribute("problemTitle", problem.get("problemTitle"));
            model.addAttribute("problemContent", problem.get("problemContent"));
            model.addAttribute("problemInputDescription", problem.get("problemInputDescription") != null ? problem.get("problemInputDescription") : "없음");
            model.addAttribute("problemOutputDescription", problem.get("problemOutputDescription") != null ? problem.get("problemOutputDescription") : "없음");
            model.addAttribute("template", problem.get("template").toString());
        } else {
            return "403";
        }
        System.out.println(problem.toString());
        

        return "code";
    }
}