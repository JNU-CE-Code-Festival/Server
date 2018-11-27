package com.example.jnucecodefestival.compileservice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Future;

import com.example.jnucecodefestival.compileservice.control.FileControl;
import com.example.jnucecodefestival.compileservice.grade.*;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

public class Compile {
    /**
     * compile
     * 
     * @param lang String ???. ????????? ?????? ??????. Available(c, cpp, node, python)
     * @param createAuthor String ???. 
     * @return
     */
    @Async("threadPoolTaskExecutor")
    public static Future<String> compile(final String lang, final String createAuthor, final String code, final String number, final int grade, final String input) throws Exception {
        Grade userGrade = null;

        // set FileLocation ex) compile/?????????14/?????????14-2018-11-10.java
        String filePath = "compile/" + createAuthor + "/" + number + "/" + lang;
        String fileName = createAuthor + "-" + new SimpleDateFormat("yy-MM-dd-kk-mm-ss").format(new Date()) + "." + lang;

        FileControl.hasFolder(filePath, grade, number, lang);
        FileControl.makeFile(filePath, fileName, code);

        // if extensions of file is java or js or py, make file that name is solution
        if(lang.equals("java") || lang.equals("js") || lang.equals("py")) FileControl.makeFile (filePath, "Solution." + lang, code);

        // get UserGrade;
        switch(grade) {
            case 1: userGrade = new FirstGrade  (filePath, fileName, lang, Integer.parseInt(number), input); break;
            case 2: userGrade = new SecondGrade (filePath, fileName, lang, Integer.parseInt(number), input); break;
            case 3: userGrade = new ThirdGrade  (filePath, fileName, lang, Integer.parseInt(number), input); break;
            default: break;
        }

        String result = userGrade.solve().toString().trim();
        return new AsyncResult<String>(result);
    }

    

    

    
    

    
}