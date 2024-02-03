package com.redcarepharmacy.findrepository.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {

    private List<GitHubRepoDTO> items;

    public List<GitHubRepoDTO> getItems() {
        return items;
    }

    public void setItems(List<GitHubRepoDTO> items) {
        this.items = items;
    }
}