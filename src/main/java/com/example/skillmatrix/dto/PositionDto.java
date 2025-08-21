package com.example.skillmatrix.dto;

import lombok.Data;

@Data
public class PositionDto {
    private Long id;
    private String name;
    private String description;
    private Integer minHardPoints;
    private Integer minSoftPoints;
}
