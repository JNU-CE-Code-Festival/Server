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
        
        // String getProblemInputQuery =
        // "select problemInput, needMultiLine from problem where problemNum=\'" + code.getNumber() + "\'";
        String getProblemInputQuery = 
        "Select problemInput, needMultiLine from problem where problemNum = ?";
        Map<String, Object> queryResult = jdbcTemplate.queryForMap(getProblemInputQuery, code.getNumber());
        Object problemInputObject = queryResult.get("problemInput");
        String problemInput = problemInputObject != null ? problemInputObject.toString() : "";
        boolean needMultiLine = (Boolean) queryResult.get("needMultiLine");
        String problemNum = Integer.toString(Integer.parseInt(code.getNumber()) % 3);
        String result = 
        Compile
            .compile(
                code.getLanguage(), 
                code.getCreateAuthor(), 
                code.getCode(), 
                problemNum,
                grade, 
                problemInput,
                needMultiLine
            )
            .get().trim();

        String answer = getAnswerFromDataBase(code, problemNum);

        int score = result.equals(answer) ? 1 : 0;
        
        try {
            jdbcTemplate.update("Update solve set language = ?, score = ?, submitCount=submitCount+1 where userName = ? and problemNum = ?", code.getLanguage(), score, code.getCreateAuthor(), problemNum);
        } catch( DataAccessException dae) {
            jdbcTemplate.update("Insert into solve(username, problemNum, submitCount, timeStamp, language, score) values(?,?,?,?,?,?)", code.getCreateAuthor(), problemNum, 1, new Timestamp(System.currentTimeMillis()), code.getLanguage(), score)
        }
        


        return result.equals(answer) ? result + " " + answer + " 정답!": result + " " + answer + "실패 !";
    }

    private String getAnswerFromDataBase(CompileRequest code, String problemNum) throws DataAccessException {
        String getAnswerQuery = 
        "select problemAnswer from problem where grade=(select grade from users where username=? and problemNum=?";
        // + code.getCreateAuthor() +
        // "\') and problemNum=\'" + code.getNumber() + "\'";
        Map<String, Object> expectedOutput = jdbcTemplate.queryForMap(getAnswerQuery, code.getCreateAuthor(), problemNum);
        
        return expectedOutput.get("problemAnswer").toString().trim();
    }
}