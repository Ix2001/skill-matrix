package com.example.skillmatrix.repository;

import com.example.skillmatrix.model.PositionSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionSkillRepository extends JpaRepository<PositionSkill, Long> {
    List<PositionSkill> findByPosition(String position);
    List<PositionSkill> findBySkillId(Long skillId);
}
