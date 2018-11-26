package com.example.jnucecodefestival.compileservice.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.springframework.security.config.method.GlobalMethodSecurityBeanDefinitionParser;

public class ProcessControl {

    
    public static StringBuilder compileProcess(StringBuilder sb, String filePath, String fileName, String lang, String input) throws Exception {    
        String executeFilePath = null;
        String[] command;
        switch(lang) {
            case "c":            
                executeFilePath = filePath + "/solution.o";
                command = new String[] {"gcc", "-c", filePath + "/" + fileName, "-o", filePath + "/solution.o"};
                Process subCompileProcess = 
                new ProcessBuilder()
                .command(command)
                .start();

                getOutputStringBuilder(sb, subCompileProcess, true);
                subCompileProcess.waitFor();

                if(sb.length() != 0) return sb;

                executeFilePath = filePath + "/a.out";
                command = new String[] {"gcc", "-O2", filePath + "/" + fileName, "-o", executeFilePath};
                break;
            case "cpp":         
                executeFilePath = filePath + "/a.out";
                command = new String[] {"g++", "-std=c++11", "-O2", "-Wno-unused-result", filePath + "/" + fileName, "-o", executeFilePath};
                break;
            case "java":
                command = new String[] {"javac", "-cp", filePath, "-encoding", "UTF-8",  filePath + "/" + "Main.java"};
                break;
            default:
                return sb;
        }

        System.out.println(Arrays.toString(command) + "명령어입니다.");
        Process compileProcess = new ProcessBuilder().command(command).start();

        getOutputStringBuilder(sb, compileProcess, true);
        compileProcess.waitFor();
        return sb;
    }

    public static StringBuilder executeProcess(StringBuilder sb, String filePath, String executeFilePath) {

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

        // public static void startSubProcess(Runnable runnable, StringBuilder sb) {
    //     Thread build = new Thread(runnable);
    //     build.start();
    //     try {
    //         build.join();
    //     } catch (InterruptedException io) {
    //         sb.append("런타임 에러!");
    //     }
    // }

    // public static void stopSubProcess() {

    // }
}