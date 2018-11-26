package com.example.jnucecodefestival.compileservice.grade;

import com.example.jnucecodefestival.compileservice.control.ProcessControl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class FirstGrade extends Grade {

    public FirstGrade(String filePath, String fileName, String lang, int problemNum, String problemInput) {
        super(filePath, fileName, lang, problemNum, problemInput);
    }

    @Override
    protected void solveProblemOne() {
        System.out.println(problemInput);
        try {
            resultStringBuilder = ProcessControl.executeProcess(
                resultStringBuilder, 
                filePath, 
                getExecuteFilePath(),
                lang,
                problemInput.split(","));
            System.out.println("Hello!진짜로????진짜로??????");
        } catch(Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    protected void solveProblemTwo() {
        try {
            resultStringBuilder = ProcessControl.executeProcess(resultStringBuilder, filePath, getExecuteFilePath(), String, null);
        } catch(Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    protected void solveProblemThree() {

    }

}