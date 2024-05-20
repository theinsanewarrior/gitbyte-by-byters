package com.poc.aide.services;

import com.poc.aide.dtos.TechRequestDto;
import com.poc.aide.dtos.TechResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface TechService {
    Map<String, List<TechResponseDto>> getAllTech();

    TechResponseDto insertTech(TechRequestDto techRequestDto);
}
