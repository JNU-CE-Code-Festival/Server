package com.example.jnucecodefestival.compileservice.grade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Future;

import com.example.jnucecodefestival.compileservice.control.ProcessControl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

public abstract class Grade {
    protected String filePath;
    protected String fileName;
    protected String lang;
    protected int problemNum;
    protected String problemInput;
    protected static int TIME_OUT = 1;

    protected StringBuilder resultStringBuilder;

    protected Grade(String filePath, String fileName, String lang, int problemNum, String problemInput) {
        this.filePath           = filePath;
        this.fileName           = fileName;
        this.lang              = lang;
        this.problemNum        = problemNum;
        this.problemInput      = problemInput;

        resultStringBuilder = new StringBuilder();
    }

    protected StringBuilder getOutputStringBuilder(StringBuilder sb, Process execute, boolean errorBool) throws IOException {
        InputStream exStream    = errorBool ? execute.getErrorStream() : execute.getInputStream();
        BufferedReader br       = new BufferedReader(new InputStreamReader(exStream));
        String line;
        while((line=br.readLine()) != null) {
            sb.append(line);
            sb.append(System.getProperty("line.separator"));
        }
        br.close();
        return sb;
    }

    
    public StringBuilder solve() throws Exception {
        ProcessControl.compileProcess(resultStringBuilder, filePath, fileName, lang, problemInput);
        
        if(resultStringBuilder.length() != 0)       return resultStringBuilder;

        switch(problemNum) {
            case 1:         solveProblemOne();      break;
            case 2:         solveProblemTwo();      break;
            case 3:         solveProblemThree();    break;
            default:                                break;
        }

        resultStringBuilder.append("양호");
        return resultStringBuilder;
    }

    protected String getExecuteFilePath() {
        switch(lang) {
            case "c":       return filePath + "/a.out";
            case "cpp":     return filePath + "/a.out";
            case "java":    return "Main";
            case "js":      return filePath + "/" + fileName;
            case "py":      return filePath + "/" + fileName;
            default:        return "";
        }
    }

    protected abstract void solveProblemOne();
    protected abstract void solveProblemTwo();
    protected abstract void solveProblemThree();

}