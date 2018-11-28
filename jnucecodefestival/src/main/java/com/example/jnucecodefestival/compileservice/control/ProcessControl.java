package com.example.jnucecodefestival.compileservice.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
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

                getOutputStringBuilder(sb, subCompileProcess, true, true);
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

                getOutputStringBuilder(sb, subCompileProcess, true, true);
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

        getOutputStringBuilder(sb, compileProcess, true, true);
        compileProcess.waitFor();
        compileProcess.destroy();
        return sb;
    }

    public static StringBuilder executeProcess(StringBuilder sb, String filePath, String executeFilePath, String lang, String[] inputParameter, boolean needMultiLine) throws Exception {

        String[] command = null;
        if(inputParameter == null) {
            switch (lang) {
                case "c":       command = new String[] {"bash", "-c", executeFilePath};                             break;
                case "cpp":     command = new String[] {"bash", "-c", executeFilePath};                             break;
                case "java":    command = new String[] {"java", "-cp", filePath, "-Dfile.encoding=utf-8", "Main"};      break;
                case "js":      command = new String[] {"node", executeFilePath};                                   break;
                case "py":      command = new String[] {"python", executeFilePath};                                break;
                default:                                                                                            break;
            }

            process(sb, needMultiLine, command);
        } else {
            for (int i = 0; i < inputParameter.length; i++) {
                if(i > 0) sb.append(",");
                String input = inputParameter[i];
                switch (lang) {
                    case "c":       command = new String[] {"bash", "-c", executeFilePath, input};                             break;
                    case "cpp":     command = new String[] {"bash", "-c", executeFilePath, input};                             break;
                    case "java":    command = new String[] {"java", "-cp", filePath, "-Dfile.encoding=utf-8", "Main", input};      break;
                    case "js":      command = new String[] {"node", executeFilePath, input};                                   break;
                    case "py":      command = new String[] {"python", executeFilePath, input};                                break;
                    default:                                                                                                   break;
                }
                process(sb, needMultiLine, command);
                
            }
        }

        
        
        
        // Global except JAVA
        // FileControl.deleteFile(filePath + "/Solution." + lang);

        // for JAVA
        FileControl.deleteFile(filePath + "/Main.class");
        FileControl.deleteFile(filePath + "/Solution.class");

        // for C, C++
        FileControl.deleteFile(filePath + "/solution.o");
         
        return sb;
    }

    private static void process(StringBuilder sb, boolean needMultiLine, String... command) throws IOException, InterruptedException {
        Process executeProcess =
        new ProcessBuilder()
        .command(command)
        .start();

        getOutputStringBuilder(sb, executeProcess, false, needMultiLine);

        if(!executeProcess.waitFor(TIME_OUT, TimeUnit.SECONDS)) {
            executeProcess.getErrorStream().close();
            executeProcess.getInputStream().close();
            executeProcess.getOutputStream().close();
            executeProcess.destroyForcibly();
            
            sb.setLength(0);
            sb.append("런타임 에러!");
        };
    }

    private static StringBuilder getOutputStringBuilder(StringBuilder sb, Process execute, boolean errorBool, boolean needMultiLine) throws IOException {
        InputStream exStream = errorBool ? execute.getErrorStream() : execute.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(exStream));
        String line;

        System.out.println(needMultiLine);
        // 나중에 문서화 필요.
        if(!needMultiLine) {
            line=br.readLine();
            if(line != null) {
                sb.append(line);
            }
        }
        
        while((line=br.readLine()) != null) {
            // if(!needMultiLine) {sb.append(",");}
            sb.append(line);

            if(needMultiLine) {sb.append(System.getProperty("line.separator"));}
            
        }
        br.close();
        return sb;
    }
}