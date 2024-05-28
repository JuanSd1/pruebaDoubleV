package org.sebastian.springprueba.mvc.tickets.dto.response;

import lombok.Data;
import org.sebastian.springprueba.mvc.tickets.dto.GitHubUserDto;

import java.util.List;

@Data
public class GitHubResponseDto {

    private List<GitHubUserDto> items;

}
