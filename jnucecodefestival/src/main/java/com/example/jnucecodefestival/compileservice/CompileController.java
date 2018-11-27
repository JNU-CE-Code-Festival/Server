package com.example.jnucecodefestival.compileservice;

import java.sql.Timestamp;
import java.util.Map;

import com.example.jnucecodefestival.model.CompileRequest;

import org.hibernate.annotations.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        String problemNum = Integer.toString(((Integer.parseInt(code.getNumber()) + 2) % 3) + 1);
        String getProblemInputQuery =
        "select problemInput, needMultiLine from problem where problemNum=" + problemNum + " and grade=" + grade;
        // String getProblemInputQuery = 
        // "Select problemInput, needMultiLine from problem where problemNum = ?";
        Map<String, Object> queryResult = jdbcTemplate.queryForMap(getProblemInputQuery);
        Object problemInputObject = queryResult.get("problemInput");
        String problemInput = problemInputObject != null ? problemInputObject.toString() : "";
        boolean needMultiLine = (Boolean) queryResult.get("needMultiLine");
        
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

        if(problemNum.equals("2") && grade == 3) {
            System.out.println("이것은 카카오문제입니다. 숫자 보정이 필요합니다.");
            try {
                String[] resultArray = result.split(",");
                String[] answerArray = result.split(",");

                for(int i = 0; i < answerArray.length; i++) {
                    double resultToInt = Double.parseDouble(resultArray[i]);
                    double answerToInt = Double.parseDouble(answerArray[i]);

                    if(Math.abs(answerToInt - resultToInt) > Math.pow(10, -6)) {
                        throw new Exception();
                    }
                }
                score = 1;
            } catch (Exception e) {
                score = 0;
            }
        }
        
        try {
            jdbcTemplate.queryForMap("select * from solve where problemNum = ?", problemNum);
            jdbcTemplate.update("Update solve set language = ?, score = ?, submitCount=submitCount+1, timeStamp = ? where userName = ? and problemNum = ?", code.getLanguage(), score, new Timestamp(System.currentTimeMillis()), code.getCreateAuthor(), problemNum);
        } catch (EmptyResultDataAccessException e) {
            jdbcTemplate.update("Insert into solve(username, problemNum, submitCount, timeStamp, language, score) values(?,?,?,?,?,?)", code.getCreateAuthor(), problemNum, 1, new Timestamp(System.currentTimeMillis()), code.getLanguage(), score);
        }

        return result.equals(answer) ? result + " " + answer + " 정답!": result + " " + answer + "실패 !";
    }

    private String getAnswerFromDataBase(CompileRequest code, String problemNum) throws DataAccessException {
        String getAnswerQuery = 
        "select problemAnswer from problem where grade=(select grade from users where username=\'"
        + code.getCreateAuthor() +
        "\') and problemNum=" + problemNum;
        Map<String, Object> expectedOutput = jdbcTemplate.queryForMap(getAnswerQuery);
        
        return expectedOutput.get("problemAnswer").toString().trim();
    }
}