package com.example.jnucecodefestival.compileservice.control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileControl {
    
    public static void hasFolder(final String filePath, final int grade, final String number, final String lang) throws IOException {
        File targetDir = new File(filePath);
        if(!targetDir.exists()) {
            targetDir.mkdirs();
            String extension = lang.equals("c") || lang.equals("cpp") ? "o" : lang;
            // ex) compile/template/1/2.py
            String originLocation = "compile/template/" + grade + "/" + lang + "/" + number + "." + extension;
            copyTemplateFile(originLocation, filePath + "/Main." + extension);
        }
    }

    public static void makeFile(final String filePath, final String fileName, final String code) {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath + "/" + fileName), "UTF-8"));
            writer.write(code);
        } catch (IOException ex) {
            System.out.println("파일 생성중 문제 발생!!");
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {}
        }
    }

    public static void deleteFile(String filePath) {
        File classFile = new File(filePath);
        if(classFile.exists()) {
            if(classFile.delete()) {
                System.out.println("파일삭제완료");
            }
        }
    }

    private static void copyTemplateFile(String origin, String destination) throws IOException {
        File originFile = new File(origin);
        File destinationFile = new File(destination);
        Files.copy(originFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

}