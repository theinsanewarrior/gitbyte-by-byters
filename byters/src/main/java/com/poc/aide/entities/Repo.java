package com.poc.aide.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "repo")
public class Repo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "repo_url")
    private String repoUrl;
    @Column(name = "script_url", columnDefinition = "TEXT")
    private String scriptUrl;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "repo_tech",
            joinColumns = {@JoinColumn(name = "repo_id")},
            inverseJoinColumns = {@JoinColumn(name = "tech_id")})
    private List<Tech> tech;
}
