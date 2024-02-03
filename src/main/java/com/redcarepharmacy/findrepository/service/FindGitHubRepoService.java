package com.redcarepharmacy.findrepository.service;

import com.redcarepharmacy.findrepository.dto.GitHubRepoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FindGitHubRepoService {

    private final FindGitHubRepoApiClient repositoryClient;

    @Autowired
    public FindGitHubRepoService(FindGitHubRepoApiClient repositoryClient) {
        this.repositoryClient = repositoryClient;
    }

    public List<GitHubRepoDTO> getPopularRepositories(int toprepositories, String language, LocalDate since) {
        return new ArrayList<>(repositoryClient.fetchRepositories(language, since, toprepositories));
    }
}