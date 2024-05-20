package com.poc.aide.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigResponseDto {
    private Long userId;
    private String username;
    private String accessToken;
    private String repo;
    private String scriptUrl;
    private Map<String, List<TechResponseDto>> techs;
}
