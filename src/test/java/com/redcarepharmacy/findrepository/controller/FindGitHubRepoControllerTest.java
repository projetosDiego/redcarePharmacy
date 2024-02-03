package com.redcarepharmacy.findrepository.controller;

import com.redcarepharmacy.findrepository.dto.GitHubRepoDTO;
import com.redcarepharmacy.findrepository.service.FindGitHubRepoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class FindGitHubRepoControllerTest {

    @Autowired
    private FindGitHubRepoController findGitHubRepoController;

    @MockBean
    private FindGitHubRepoService findGitHubRepoService;

    @Test
    void testGetPopularRepositories() throws Exception {
        // Arrange
        GitHubRepoDTO gitHubRepoDTO1 = new GitHubRepoDTO("Kratos", "Kratos full", "html_url", "java", 100);
        GitHubRepoDTO gitHubRepoDTO2 = new GitHubRepoDTO("Link", "Link full", "html_url", "Node", 50);
        List<GitHubRepoDTO> gitHubRepoDTOListMock = List.of(gitHubRepoDTO1, gitHubRepoDTO2);

        when(findGitHubRepoService.getPopularRepositories(10, null, LocalDate.parse("2020-01-01"))).thenReturn(gitHubRepoDTOListMock);
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/fetchrepositories").param("language", "java");
        MockHttpServletRequestBuilder paramResult2 = paramResult.param("since", String.valueOf(LocalDate.of(2020, 1, 1)));
        MockHttpServletRequestBuilder requestBuilder = paramResult2.param("toprepositories", String.valueOf(10));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(findGitHubRepoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}