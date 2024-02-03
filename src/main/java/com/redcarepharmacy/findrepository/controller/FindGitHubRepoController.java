package com.redcarepharmacy.findrepository.controller;

import com.redcarepharmacy.findrepository.dto.GitHubRepoDTO;
import com.redcarepharmacy.findrepository.service.FindGitHubRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("fetchrepositories")
public class FindGitHubRepoController {

    private final FindGitHubRepoService repositoryService;

    @Autowired
    public FindGitHubRepoController(FindGitHubRepoService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @GetMapping
    public ResponseEntity<List<GitHubRepoDTO>> getPopularRepositories(@RequestParam(defaultValue = "2008-01-01") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate since,
                                                                      @RequestParam(defaultValue = "") String language,
                                                                      @RequestParam(defaultValue = "10") int toprepositories) {
        return new ResponseEntity<>(repositoryService.getPopularRepositories(toprepositories, language, since), HttpStatus.OK);
    }
}