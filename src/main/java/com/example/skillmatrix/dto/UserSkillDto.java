package com.example.skillmatrix.dto;

import lombok.Data;

@Data
public class UserSkillDto {
    private Long id;
    private Long userId;
    private Long skillId;
    private String skillName;
    private String skillType;
    private Integer points;
    private Integer maxPoints;
}
