package com.poc.aide.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechResponseDto {
    private Long id;
    private String name;
    private String version;
    private String techType;
}
