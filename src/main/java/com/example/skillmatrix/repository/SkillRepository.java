package com.example.skillmatrix.repository;

import com.example.skillmatrix.model.Skill;
import com.example.skillmatrix.model.Skill.SkillType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByCategoryId(Long categoryId);
    List<Skill> findByType(SkillType type);
    boolean existsByNameAndCategoryId(String name, Long categoryId);
}
