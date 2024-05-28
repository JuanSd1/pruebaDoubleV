package org.sebastian.springprueba.mvc.tickets.service;

import org.sebastian.springprueba.mvc.tickets.dto.GitHubUserDto;

import java.util.List;

public interface IGitHubService {

    List<GitHubUserDto> getUsers(String name);

}
