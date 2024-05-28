package org.sebastian.springprueba.mvc.tickets.controller;

import org.sebastian.springprueba.mvc.tickets.dto.GitHubUserDto;
import org.sebastian.springprueba.mvc.tickets.service.IGitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/github")
public class GitHubController {

    @Autowired
    private IGitHubService gitHubService;

    @GetMapping("/users")
    public List<GitHubUserDto> getUsers(@RequestParam String name) {
        return gitHubService.getUsers(name);
    }

}
