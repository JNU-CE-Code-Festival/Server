package com.example.jnucecodefestival.model;

import com.fasterxml.jackson.annotation.JsonProperty;

// @XmlRootElement(name="compile")
public class CompileRequest {

    @JsonProperty("code")
    private String code;
    @JsonProperty("language")
    private String language;
    @JsonProperty("createAuthor")
    private String createAuthor;


    public String getCode() {
        return code;
    }

    public String getLanguage() {
        return language;
    }

    public String getCreateAuthor() {
        return createAuthor;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setCreateAuthor(String createAuthor) {
        this.createAuthor = createAuthor;
    }

    @Override
    public String toString() {
        return String.format("CompileRequest : { code : %s , language : %s, author : %s }", code, language, createAuthor);
    }

}