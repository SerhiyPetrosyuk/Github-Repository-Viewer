package com.mlsdev.serhiy.domain.model;

/**
 * Created by Serhiy Petrosyuk on 17.04.15.
 */
public class GithubRepository {

    private String  name;
    private String  language;
    private Integer starsNumber;
    private Integer forksNumber;

    public GithubRepository() {
    }

    public GithubRepository(String name, String language, Integer starsNumber, Integer forksNumber) {
        this.name = name;
        this.language = language;
        this.starsNumber = starsNumber;
        this.forksNumber = forksNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setStarsNumber(Integer starsNumber) {
        this.starsNumber = starsNumber;
    }

    public void setForksNumber(Integer forksNumber) {
        this.forksNumber = forksNumber;
    }

    public Integer getForksNumber() {
        return forksNumber;
    }

    public Integer getStarsNumber() {
        return starsNumber;
    }

    public String getLanguage() {
        return language;
    }

    public String getName() {
        return name;
    }
}
