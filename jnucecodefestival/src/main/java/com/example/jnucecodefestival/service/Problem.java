package com.example.jnucecodefestival.service;

public class Problem {
    private int problemNum;
    private String title;           // ?????? ??????
    private String content;         // ?????? ??????
    private String answer;          // ??????(???????????? ??????)
    private String testcase;        // ??????????????????
    private String answerInput;     // ??????(?????????????????? ??????)

    public int getProblemNum() {
        return problemNum;
    }

    public void setProblemNum(int problemNum) {
        this.problemNum = problemNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTestcase() {
        return testcase;
    }

    public void setTestcase(String testcase) {
        this.testcase = testcase;
    }

    public String getAnswerInput() {
        return answerInput;
    }

    public void setAnswerInput(String answerInput) {
        this.answerInput = answerInput;
    }
}
