package com.poc.aide.repositories;

import com.poc.aide.entities.Tech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechRepository extends JpaRepository<Tech, Long> {
    List<Tech> findAllByIdIn(List<Long> ids);
}
