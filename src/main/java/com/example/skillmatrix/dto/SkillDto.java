package com.example.skillmatrix.dto;

import com.example.skillmatrix.model.Skill.SkillType;
import lombok.Data;

@Data
public class SkillDto {
    private Long id;
    private String name;
    private String description;
    private SkillType type;
    private Long categoryId;
    private Integer maxPoints;
}
