package com.poc.aide.entities;

import com.poc.aide.enums.TechType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tech")
public class Tech {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "version")
    private String version;
    @Column(name = "type")
    private TechType techType;
    @ManyToMany(mappedBy = "tech")
    private List<Repo> repos;
}
