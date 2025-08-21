package com.example.skillmatrix.controller;

import com.example.skillmatrix.dto.PositionDto;
import com.example.skillmatrix.service.PositionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
@RequiredArgsConstructor
@Tag(name = "Positions", description = "API для управления должностями")
public class PositionController {
    
    private final PositionService positionService;
    
    @GetMapping
    @Operation(summary = "Получить все должности", description = "Получить список всех должностей")
    public ResponseEntity<List<PositionDto>> getAllPositions() {
        return ResponseEntity.ok(positionService.getAllPositions());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Получить должность по ID", description = "Получить детальную информацию о должности")
    public ResponseEntity<PositionDto> getPositionById(@PathVariable Long id) {
        return ResponseEntity.ok(positionService.getPositionById(id));
    }
    
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @Operation(summary = "Создать должность", description = "Создать новую должность (только ADMIN/MODERATOR)")
    public ResponseEntity<PositionDto> createPosition(@RequestBody PositionDto positionDto) {
        return ResponseEntity.ok(positionService.createPosition(positionDto));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @Operation(summary = "Обновить должность", description = "Обновить информацию о должности (только ADMIN/MODERATOR)")
    public ResponseEntity<PositionDto> updatePosition(@PathVariable Long id, @RequestBody PositionDto positionDto) {
        return ResponseEntity.ok(positionService.updatePosition(id, positionDto));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @Operation(summary = "Удалить должность", description = "Удалить должность по ID (только ADMIN/MODERATOR)")
    public ResponseEntity<Void> deletePosition(@PathVariable Long id) {
        positionService.deletePosition(id);
        return ResponseEntity.ok().build();
    }
}
