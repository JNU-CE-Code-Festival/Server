package com.example.jnucecodefestival.compileservice.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.springframework.security.config.method.GlobalMethodSecurityBeanDefinitionParser;

public class ProcessControl {
    public static final long TIME_OUT = 1;
    
    public static StringBuilder compileProcess(StringBuilder sb, String filePath, String fileName, String lang, String input) throws Exception {    
        String executeFilePath = null;
        String[] command;
        Process subCompileProcess = null;
        switch(lang) {
            case "c":            
                executeFilePath = filePath + "/solution.o";
                command = new String[] {"gcc", "-c", filePath + "/" + fileName, "-o", executeFilePath, "-O2"};
                subCompileProcess = 
                new ProcessBuilder()
                .command(command)
                .start();

                getOutputStringBuilder(sb, subCompileProcess, true);
                subCompileProcess.waitFor();
                subCompileProcess.destroy();
                if(sb.length() != 0) return sb;

                executeFilePath = filePath + "/a.out";
                command = new String[] {"gcc", filePath + "/Main.o", filePath + "/solution.o", "-o", executeFilePath};
                break;
            case "cpp":         
                executeFilePath = filePath + "/solution.o";
                command = new String[] {"g++", "-c", "-std=c++11", "-O2", "-Wno-unused-result", filePath + "/" + fileName, "-o", executeFilePath};
                subCompileProcess = 
                new ProcessBuilder()
                .command(command)
                .start();

                getOutputStringBuilder(sb, subCompileProcess, true);
                subCompileProcess.waitFor();
                subCompileProcess.destroy();

                executeFilePath = filePath + "/a.out";
                command = new String[] {"g++", filePath + "/Main.o", filePath + "/solution.o", "-o", executeFilePath};
                break;
            case "java":
                command = new String[] {"javac", filePath + "/" + "Main.java", "-cp", filePath, "-encoding", "UTF-8"};
                break;
            default:
                return sb;
        }
        Process compileProcess = new ProcessBuilder().command(command).start();

        getOutputStringBuilder(sb, compileProcess, true);
        compileProcess.waitFor();
        compileProcess.destroy();
        return sb;
    }

    public static StringBuilder executeProcess(StringBuilder sb, String filePath, String executeFilePath, String lang, String[] inputParameter) throws Exception {

        String[] command = null;
        if(inputParameter == null) {
            switch (lang) {
                case "c":       command = new String[] {"bash", "-c", executeFilePath};                             break;
                case "cpp":     command = new String[] {"bash", "-c", executeFilePath};                             break;
                case "java":    command = new String[] {"java", "-cp", filePath, "-Dfile.encoding=utf-8", "Main"};      break;
                case "js":      command = new String[] {"node", executeFilePath};                                   break;
                case "py":      command = new String[] {"python3", executeFilePath};                                break;
                default:                                                                                            break;
            }
        } else {
            for (String input : inputParameter) {
                switch (lang) {
                    case "c":       command = new String[] {"bash", "-c", executeFilePath, input};                             break;
                    case "cpp":     command = new String[] {"bash", "-c", executeFilePath, input};                             break;
                    case "java":    command = new String[] {"java", "-cp", filePath, "-Dfile.encoding=utf-8", "Main", input};      break;
                    case "js":      command = new String[] {"node", executeFilePath, input};                                   break;
                    case "py":      command = new String[] {"python3", executeFilePath, input};                                break;
                    default:                                                                                                   break;
                }
            }
        }

        Process executeProcess =
        new ProcessBuilder()
        .command(command)
        .start();

        getOutputStringBuilder(sb, executeProcess, false);

        if(!executeProcess.waitFor(TIME_OUT, TimeUnit.SECONDS)) {
            executeProcess.getErrorStream().close();
            executeProcess.getInputStream().close();
            executeProcess.getOutputStream().close();
            executeProcess.destroy();
            
            sb.setLength(0);
            sb.append("런타임 에러!");
        };
        
        
        // Global except JAVA
        FileControl.deleteFile(executeFilePath);

        // for JAVA
        FileControl.deleteFile(filePath + "/Main.class");
        FileControl.deleteFile(filePath + "/Solution.class");

        // for C, C++
        FileControl.deleteFile(filePath + "/solution.o");
         
        return sb;
    }

    private static StringBuilder getOutputStringBuilder(StringBuilder sb, Process execute, boolean errorBool) throws IOException {
        InputStream exStream = errorBool ? execute.getErrorStream() : execute.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(exStream));
        String line;
        while((line=br.readLine()) != null) {
            sb.append(line);
            sb.append(System.getProperty("line.separator"));
        }
        br.close();
        return sb;
    }
}