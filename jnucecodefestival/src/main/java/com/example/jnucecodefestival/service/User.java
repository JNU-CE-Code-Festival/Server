package com.example.jnucecodefestival.service;

public class User {
    private String id;
    private String password;
    private String name;
    private int grade;
    private int scoreGet;       // 현재 스코어
    private boolean firstBool;  // 정답을 맞추었는가 못맞추었는가
    private boolean secondBool;  // 정답을 맞추었는가 못맞추었는가
    private boolean thirdBool;  // 정답을 맞추었는가 못맞추었는가

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    public int getScoreGet() {
        return scoreGet;
    }

    public void setScoreGet(int scoreGet) {
        this.scoreGet = scoreGet;
    }

    public boolean getFirstBool() {
        return firstBool;
    }

    public void setFirstBool(boolean firstBool) {
        this.firstBool = firstBool;
    }

    public boolean getSecondBool() {
        return secondBool;
    }

    public void setSecondBool(boolean secondBool) {
        this.secondBool = secondBool;
    }

    public boolean getThirdBool() {
        return thirdBool;
    }

    public void setThirdBool(boolean thirdBool) {
        this.thirdBool = thirdBool;
    }
}
