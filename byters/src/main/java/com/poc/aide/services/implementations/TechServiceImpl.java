package com.poc.aide.services.implementations;

import com.poc.aide.dtos.TechRequestDto;
import com.poc.aide.dtos.TechResponseDto;
import com.poc.aide.entities.Tech;
import com.poc.aide.enums.TechType;
import com.poc.aide.repositories.TechRepository;
import com.poc.aide.services.TechService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TechServiceImpl implements TechService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TechRepository techRepository;

    @Override
    public Map<String, List<TechResponseDto>> getAllTech() {
        List<Tech> techList = techRepository.findAll();
        List<TechResponseDto> techResponseDtoList = techList
                .stream()
                .map(tech -> new TechResponseDto(tech.getId(), tech.getName(), tech.getVersion(), tech.getTechType().name()))
                .collect(Collectors.toList());
        return techResponseDtoList.stream().collect(Collectors.groupingBy(TechResponseDto::getTechType));
    }

    @Override
    public TechResponseDto insertTech(TechRequestDto techRequestDto) {
        Tech tech = new Tech();
        tech.setName(techRequestDto.getName());
        tech.setVersion(techRequestDto.getVersion());
        tech.setTechType(TechType.valueOf(techRequestDto.getTechType()));
        tech = techRepository.save(tech);
        return new TechResponseDto(tech.getId(), tech.getName(), tech.getVersion(), tech.getTechType().name());
    }
}
