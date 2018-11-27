package com.example.jnucecodefestival.compileservice.grade;

import com.example.jnucecodefestival.compileservice.control.FileControl;
import com.example.jnucecodefestival.compileservice.control.ProcessControl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class FirstGrade extends Grade {

    public FirstGrade(String filePath, String fileName, String lang, int problemNum, String problemInput, boolean needMultiLine) {
        super(filePath, fileName, lang, problemNum, problemInput, needMultiLine);
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
                problemInput.split(","), needMultiLine);

                String example = "11 22";
        } catch(Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    protected void solveProblemTwo() {
        try {
            resultStringBuilder = ProcessControl.executeProcess(resultStringBuilder, filePath, getExecuteFilePath(), lang, null, needMultiLine);
        } catch(Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    protected void solveProblemThree() {

    }

}