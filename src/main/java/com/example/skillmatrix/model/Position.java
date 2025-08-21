package com.example.skillmatrix.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "positions")
@Data
public class Position {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String name;
    
    @Column(nullable = false)
    private Integer minHardPoints = 0;
    
    @Column(nullable = false)
    private Integer minSoftPoints = 0;
    
    @Column(columnDefinition = "TEXT")
    private String description;
}
