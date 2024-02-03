package com.redcarepharmacy.findrepository.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import com.redcarepharmacy.findrepository.dto.ApiResponse;
import com.redcarepharmacy.findrepository.dto.GitHubRepoDTO;
import com.redcarepharmacy.findrepository.exception.NoRepositoriesFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class FindGitHubRepoApiClientTest {

    @Autowired
    private FindGitHubRepoApiClient findGitHubRepoApiClient;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void test_fetchRepositories_method_with_no_language_filter(){
        //arrange
        GitHubRepoDTO gitHubRepoDTO1 = new GitHubRepoDTO("Kratos", "Kratos full", "html_url", "java", 100);
        GitHubRepoDTO gitHubRepoDTO2 = new GitHubRepoDTO("Link", "Link full", "html_url", "Node", 50);
        ApiResponse apiResponseMock = new ApiResponse();
        apiResponseMock.setItems(List.of(gitHubRepoDTO1, gitHubRepoDTO2));

        when(restTemplate.getForObject(anyString(), eq(ApiResponse.class))).thenReturn(apiResponseMock);

        //act
        List<GitHubRepoDTO> response = findGitHubRepoApiClient.fetchRepositories(null, LocalDate.parse("2020-01-01"), 10);

        //assert
        Assertions.assertNotNull(response);
        Assertions.assertSame(apiResponseMock.getItems(), response);
    }

    @Test
    public void test_fetchRepositories_method_with_language_filter(){
        //arrange
        GitHubRepoDTO gitHubRepoDTO1 = new GitHubRepoDTO("Kratos", "Kratos full", "html_url", "java", 100);
        ApiResponse apiResponseMock = new ApiResponse();
        apiResponseMock.setItems(List.of(gitHubRepoDTO1));

        when(restTemplate.getForObject(anyString(), eq(ApiResponse.class))).thenReturn(apiResponseMock);

        //act
        List<GitHubRepoDTO> response = findGitHubRepoApiClient.fetchRepositories("java", LocalDate.parse("2020-01-01"), 10);

        //assert
        Assertions.assertNotNull(response);
        Assertions.assertSame(apiResponseMock.getItems(), response);
    }

    @Test
    public void test_fetchRepositories_method_with_no_repository_found_exception(){
        //arrange
        when(restTemplate.getForObject(anyString(), eq(ApiResponse.class))).thenReturn(null);

        //act
        Exception exception = assertThrows(
                NoRepositoriesFoundException.class,
                () -> findGitHubRepoApiClient.fetchRepositories("java", LocalDate.parse("2000-01-01"), 10));

        //assert
        Assertions.assertEquals("No repositories found based on the provided criteria.", exception.getMessage());
    }
}