package dev.subrotokumar.project.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dev.subrotokumar.project.entity.Project;
import dev.subrotokumar.project.entity.ProjectStatus;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Modifying
    @Transactional
    @Query("""
        update Project p 
        set p.status = :status 
        where p.id = :id and p.userId = :userId
    """)
    void setStatus(@Param("userId") int userId, @Param("id") Long id, @Param("status") ProjectStatus status);

    @Modifying
    @Transactional
    @Query("""
        delete from Project p
        where p.id = :id and p.userId = :userId
    """)
    void deleteProjectWhereIdAndUserIdEquals(@Param("id") Long id, @Param("userId") Integer userId);

    @Modifying
    @Transactional
    @Query("""
        update Project p 
        set p.title = COALESCE(:title, p.title), 
        p.description = COALESCE(:description, p.description),
        p.githubUrl = COALESCE(:githubUrl, p.githubUrl),
        p.status = COALESCE(:status, p.status),
        p.lastDeployedAt = COALESCE(:lastDeployedAt, p.lastDeployedAt),
        p.tags = COALESCE(:tags, p.tags)
        where p.id = :id and p.userId = :userId
    """)
    void updateProject(
            @Param("userId") Integer userId,
            @Param("id") Long id,
            @Param("title") String title,
            @Param("description") String description,
            @Param("githubUrl") String githubUrl,
            @Param("status") ProjectStatus status,
            @Param("lastDeployedAt") LocalDateTime lastDeployedAt,
            @Param("tags") List<String> tags
    );

    List<Project> findAllByUserId(Integer userId);
}
