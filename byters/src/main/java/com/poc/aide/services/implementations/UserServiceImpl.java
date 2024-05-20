package com.poc.aide.services.implementations;

import com.poc.aide.dtos.*;
import com.poc.aide.entities.Repo;
import com.poc.aide.entities.Tech;
import com.poc.aide.entities.User;
import com.poc.aide.enums.Role;
import com.poc.aide.repositories.RepoRepository;
import com.poc.aide.repositories.TechRepository;
import com.poc.aide.repositories.UserRepository;
import com.poc.aide.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RepoRepository repoRepository;
    @Autowired
    private TechRepository techRepository;

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setRole(Role.USER);
        user = userRepository.save(user);
        return new UserResponseDto(user.getId(), user.getUsername(), user.getRole().name());
    }

    @Override
    public ConfigResponseDto configureUserProject(ConfigDto configDto) {
        User user = new User();
        user.setUsername(configDto.getUsername());
        user.setRole(Role.USER);
        user.setAccessToken(configDto.getAccessToken());
        Repo repo = new Repo();
        repo.setRepoUrl(configDto.getRepo());
        repo.setUser(user);
        List<Tech> techs = techRepository.findAllByIdIn(configDto.getTechs());
        for (Tech tech: techs){
            List<Repo> repos = tech.getRepos()==null?new ArrayList<>():tech.getRepos();
            repos.add(repo);
            tech.setRepos(repos);
        }
        repo.setTech(techs);
        List<Repo> repos = user.getRepos()==null?new ArrayList<>():user.getRepos();
        repos.add(repo);
        user.setRepos(repos);
        user = userRepository.save(user);
        ConfigResponseDto response = new ConfigResponseDto();
        response.setUserId(user.getId());
        response.setUsername(user.getUsername());
        response.setAccessToken(user.getAccessToken());
        response.setRepo(repo.getRepoUrl());
        List<TechResponseDto> techResponse = techs
                .stream()
                .map(tech -> new TechResponseDto(tech.getId(), tech.getName(), tech.getVersion(), tech.getTechType().name()))
                .collect(Collectors.toList());
        response.setTechs(techResponse.stream().collect(Collectors.groupingBy(TechResponseDto::getTechType)));
        return response;
    }

    @Override
    public ConfigResponseDto getConfiguration(Long repoId) {
        Repo repo = repoRepository.findById(repoId).get();
        User user = repo.getUser();
        ConfigResponseDto configResponseDto = new ConfigResponseDto();
        configResponseDto.setUserId(user.getId());
        configResponseDto.setUsername(user.getUsername());
        configResponseDto.setAccessToken(user.getAccessToken());
        configResponseDto.setRepo(repo.getRepoUrl());
        configResponseDto.setScriptUrl(repo.getScriptUrl());
        Map<String, List<TechResponseDto>> techs = repo.getTech()
                .stream()
                .map(tech -> new TechResponseDto(tech.getId(), tech.getName(), tech.getVersion(), tech.getTechType().name()))
                .collect(Collectors.toList())
                .stream()
                .collect(Collectors.groupingBy(TechResponseDto::getTechType));
        configResponseDto.setTechs(techs);
        return configResponseDto;
    }
}
