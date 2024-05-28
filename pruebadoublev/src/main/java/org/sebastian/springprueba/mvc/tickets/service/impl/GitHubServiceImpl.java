package org.sebastian.springprueba.mvc.tickets.service.impl;

import org.sebastian.springprueba.mvc.tickets.dto.GitHubUserDto;
import org.sebastian.springprueba.mvc.tickets.dto.response.GitHubResponseDto;
import org.sebastian.springprueba.mvc.tickets.service.IGitHubService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GitHubServiceImpl implements IGitHubService {

    @Value("${github.api.url}")
    private String githubApiUrl;

    @Override
    public List<GitHubUserDto> getUsers(String name) {
        RestTemplate restTemplate = new RestTemplate();
        String url = githubApiUrl + name;
        GitHubResponseDto response = restTemplate.getForObject(url, GitHubResponseDto.class);
        return response.getItems();
    }

}
