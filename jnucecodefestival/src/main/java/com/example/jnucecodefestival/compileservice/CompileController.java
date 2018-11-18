package com.example.jnucecodefestival.compileservice;

import java.io.Writer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jnucecodefestival.model.CompileRequest;




@Controller
public class CompileController {
    Writer writer = null;
    @GetMapping(
        value="/compile"
    )
    @ResponseBody
    public String getCompile(@RequestParam String code) {
        return "안돼";
    }

    @PostMapping(
        value="/compile",
        produces= "application/json"
    )
    @ResponseBody
    public String postCompile(@RequestBody CompileRequest code) {
        String result = Compile.compile(code.getLanguage(), code.getCreateAuthor(), code.getCode());
        
        // System.out.println(code.toString());
        return result;
    }
}