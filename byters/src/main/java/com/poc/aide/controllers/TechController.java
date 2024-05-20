package com.poc.aide.controllers;

import com.poc.aide.dtos.TechRequestDto;
import com.poc.aide.dtos.TechResponseDto;
import com.poc.aide.services.TechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api")
public class TechController {
    @Autowired
    private TechService techService;
    @GetMapping("/techstack/dropdown")
    public ResponseEntity<Map<String, List<TechResponseDto>>> getAllTech(){
        return ResponseEntity.ok(techService.getAllTech());
    }
    @PostMapping("/techstack/insert")
    public ResponseEntity<TechResponseDto> insertTechStack(@RequestBody TechRequestDto techRequestDto){
        return ResponseEntity.ok(techService.insertTech(techRequestDto));
    }
}
