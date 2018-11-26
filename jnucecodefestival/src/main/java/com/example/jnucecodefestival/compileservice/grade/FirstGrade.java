package com.example.jnucecodefestival.compileservice.grade;

import com.example.jnucecodefestival.compileservice.control.ProcessControl;

public class FirstGrade extends Grade {

    public FirstGrade(String filePath, String fileName, String lang, int problemNum, String input) {
        super(filePath, fileName, lang, problemNum, input);
    }

    @Override
    protected void solveProblemOne() {
        try {
            resultStringBuilder = ProcessControl.executeProcess(resultStringBuilder, filePath, getExecuteFilePath());
            System.out.println("Hello!");
        } catch(Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    protected void solveProblemTwo() {
        
    }

    @Override
    protected void solveProblemThree() {

    }

}