package com.example.jnucecodefestival.service;

public class Problem {
    private int id;
    private int grade;
    private int problemNum;
    private String problemTitle;
    private String problemContent;
    private String problemTestCase;
    private String problemTestCaseAnswer;
    private String problemInput;
    private String problemAnswer;
    private String problemInputDescription;
    private String problemOutputDescription;
    private String template;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getProblemNum() {
        return problemNum;
    }

    public void setProblemNum(int problemNum) {
        this.problemNum = problemNum;
    }

    public String getProblemTitle() {
        return problemTitle;
    }

    public void setProblemTitle(String problemTitle) {
        this.problemTitle = problemTitle;
    }

    public String getProblemContent() {
        return problemContent;
    }

    public void setProblemContent(String problemContent) {
        this.problemContent = problemContent;
    }

    public String getProblemTestCase() {
        return problemTestCase;
    }

    public void setProblemTestCase(String problemTestCase) {
        this.problemTestCase = problemTestCase;
    }

    public String getProblemTestCaseAnswer() {
        return problemTestCaseAnswer;
    }

    public void setProblemTestCaseAnswer(String problemTestCaseAnswer) {
        this.problemTestCaseAnswer = problemTestCaseAnswer;
    }

    public String getProblemInput() {
        return problemInput;
    }

    public void setProblemInput(String problemInput) {
        this.problemInput = problemInput;
    }

    public String getProblemAnswer() {
        return problemAnswer;
    }

    public void setProblemAnswer(String problemAnswer) {
        this.problemAnswer = problemAnswer;
    }

    public String getProblemInputDescription() {
        return problemInputDescription;
    }

    public void setProblemInputDescription(String problemInputDescription) {
        this.problemInputDescription = problemInputDescription;
    }

    public String getProblemOutputDescription() {
        return problemOutputDescription;
    }

    public void setProblemOutputDescription(String problemOutputDescription) {
        this.problemOutputDescription = problemOutputDescription;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
