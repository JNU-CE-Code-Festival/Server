package com.example.jnucecodefestival.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/main")
public class MainPageController {
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        String str = "Hello World";

        model.addAttribute("problem", str);

        return "main";
    }
}
