package com.redcarepharmacy.findrepository.dto;

public class GitHubRepoDTO {

    private String name;
    private String full_name;
    private String html_url;
    private String language;
    private int stargazers_count;

    public GitHubRepoDTO(String name, String full_name, String html_url, String language, int stargazers_count) {
        this.name = name;
        this.full_name = full_name;
        this.html_url = html_url;
        this.language = language;
        this.stargazers_count = stargazers_count;
    }

    public GitHubRepoDTO() {
    }

    public GitHubRepoDTO(String repo1, String java) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }
}