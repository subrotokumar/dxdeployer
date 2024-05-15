package dev.subrotokumar.project.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique=true, updatable=false)
    private String slug;

    @Column(nullable=false)
    private String title;

    @Column()
    private String description;

    @Column(nullable = false, unique=true)
    private String githubUrl;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @Enumerated(EnumType.STRING)
    private ProjectType type;

    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT 'NOW()'")
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Integer userId;

    @Column(insertable=false)
    private LocalDateTime lastDeployedAt;

    @ElementCollection
    @CollectionTable()
    private List<String> tags;
}
