package com.example.jnucecodefestival.compileservice;

import java.util.Map;

import com.example.jnucecodefestival.model.CompileRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class CompileController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
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
    public String postCompile(@RequestBody CompileRequest code) throws Exception {
        String getGradeQuery = 
        "select grade from users where username=\'" + code.getCreateAuthor() + "\'";
        int grade = Integer.parseInt(jdbcTemplate.queryForMap(getGradeQuery).get("grade").toString());
        
        String getProblemInputQuery =
        "select problemInput from problem where problemNum=\'" + code.getNumber() + "\'";
        
        Object problemInputObject = jdbcTemplate.queryForMap(getProblemInputQuery).get("problemInput");
        String problemInput = problemInputObject != null ? problemInputObject.toString() : "";
        String result = 
        Compile
            .compile(
                code.getLanguage(), 
                code.getCreateAuthor(), 
                code.getCode(), 
                code.getNumber(), 
                grade, 
                problemInput
            )
            .get().trim();

        String answer = getAnswerFromDataBase(code);

        if(result.equals(answer)) {

        }

        return result.equals(answer) ? result + " " + answer + " 정답!": result + " " + answer + "실패 !";
    }

    private String getAnswerFromDataBase(CompileRequest code) throws DataAccessException {
        String getAnswerQuery = 
        "select problemAnswer from problem where grade=(select grade from users where username=\'"
        + code.getCreateAuthor() +
        "\') and problemNum=\'" + code.getNumber() + "\'";
        Map<String, Object> expectedOutput = jdbcTemplate.queryForMap(getAnswerQuery);
        
        return expectedOutput.get("problemAnswer").toString().trim();
    }
}