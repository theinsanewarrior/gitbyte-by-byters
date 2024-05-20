package com.poc.aide.services;

import com.poc.aide.dtos.ConfigDto;
import com.poc.aide.dtos.ConfigResponseDto;
import com.poc.aide.dtos.UserRequestDto;
import com.poc.aide.dtos.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserResponseDto addUser(UserRequestDto userRequestDto);

    ConfigResponseDto configureUserProject(ConfigDto configDto);

    ConfigResponseDto getConfiguration(Long repoId);
}
