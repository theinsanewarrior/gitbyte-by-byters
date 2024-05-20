package com.poc.aide.controllers;

import com.poc.aide.dtos.ConfigDto;
import com.poc.aide.dtos.ConfigResponseDto;
import com.poc.aide.dtos.UserRequestDto;
import com.poc.aide.dtos.UserResponseDto;
import com.poc.aide.services.UploadService;
import com.poc.aide.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/api")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UploadService uploadService;

    @PostMapping("/user")
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.ok(userService.addUser(userRequestDto));
    }
    @PostMapping("/user/configure")
    public ResponseEntity<ConfigResponseDto> configureUserProject(@RequestBody ConfigDto configDto){
        return ResponseEntity.ok(userService.configureUserProject(configDto));
    }
    @PostMapping(value = "/user/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestPart MultipartFile file, @RequestParam Long repoId){
        return ResponseEntity.ok(uploadService.uploadFile(file, repoId));
    }
    @GetMapping("/user/configuration")
    public ResponseEntity<ConfigResponseDto> getConfiguration(@RequestParam Long repoId){
        return ResponseEntity.ok(userService.getConfiguration(repoId));
    }
}
