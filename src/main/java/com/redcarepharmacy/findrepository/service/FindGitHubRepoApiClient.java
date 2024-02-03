package com.redcarepharmacy.findrepository.service;

import com.redcarepharmacy.findrepository.dto.ApiResponse;
import com.redcarepharmacy.findrepository.dto.GitHubRepoDTO;
import com.redcarepharmacy.findrepository.exception.NoRepositoriesFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Component
public class FindGitHubRepoApiClient {

    @Value("${github.api.url}")
    private String githubApiUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public FindGitHubRepoApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<GitHubRepoDTO> fetchRepositories(String language, LocalDate since, int toprepositories) {
        String apiUrl;
        if (language != null && !language.isEmpty()) {
            apiUrl = String.format("%s?q=stars:>=1&sort=stars&per_page=%s&language:%s&created:%s&order=desc", githubApiUrl, toprepositories, language, since);
        } else {
            apiUrl = String.format("%s?q=stars:>=1&sort=stars&per_page=%s&created:%s&order=desc", githubApiUrl, since, toprepositories);
        }

        ApiResponse response = restTemplate.getForObject(apiUrl, ApiResponse.class);

        if (response != null && response.getItems() != null) {
            return response.getItems();
        } else {
            throw new NoRepositoriesFoundException("No repositories found based on the provided criteria.");
        }
    }
}