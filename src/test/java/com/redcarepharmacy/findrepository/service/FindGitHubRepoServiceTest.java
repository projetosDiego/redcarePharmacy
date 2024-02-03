package com.redcarepharmacy.findrepository.service;

import com.redcarepharmacy.findrepository.dto.GitHubRepoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class FindGitHubRepoServiceTest {

    @Autowired
    private FindGitHubRepoService findGitHubRepoService;

    @MockBean
    private FindGitHubRepoApiClient findGitHubRepoApiClient;

    @Test
    public void test_getPopularRepositories(){
        //arrange
        GitHubRepoDTO gitHubRepoDTO1 = new GitHubRepoDTO("Kratos", "Kratos full", "html_url", "java", 100);
        GitHubRepoDTO gitHubRepoDTO2 = new GitHubRepoDTO("Link", "Link full", "html_url", "Node", 50);
        List<GitHubRepoDTO> gitHubRepoDTOListMock = List.of(gitHubRepoDTO1, gitHubRepoDTO2);

        when(findGitHubRepoApiClient.fetchRepositories(null, LocalDate.parse("2020-01-01"), 10)).thenReturn(gitHubRepoDTOListMock);

        //act
        List<GitHubRepoDTO> response = findGitHubRepoService.getPopularRepositories(10,null, LocalDate.parse("2020-01-01"));

        //assert
        Assertions.assertNotNull(response);
    }
}