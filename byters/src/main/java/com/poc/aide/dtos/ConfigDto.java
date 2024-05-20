package com.poc.aide.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigDto {
    private String username;
    private String accessToken;
    private String repo;
    private List<Long> techs;
}
