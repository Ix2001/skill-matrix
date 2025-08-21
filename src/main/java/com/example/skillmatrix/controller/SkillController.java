package com.example.skillmatrix.controller;

import com.example.skillmatrix.dto.SkillDto;
import com.example.skillmatrix.service.SkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
@Tag(name = "Skills", description = "API для управления навыками")
public class SkillController {
    
    private final SkillService skillService;
    
    @GetMapping
    @Operation(summary = "Получить все навыки", description = "Получить список всех навыков")
    public ResponseEntity<List<SkillDto>> getAllSkills() {
        return ResponseEntity.ok(skillService.getAllSkills());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Получить навык по ID", description = "Получить детальную информацию о навыке")
    public ResponseEntity<SkillDto> getSkillById(@PathVariable Long id) {
        return ResponseEntity.ok(skillService.getSkillById(id));
    }
    
    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Получить навыки по категории", description = "Получить все навыки для конкретной категории")
    public ResponseEntity<List<SkillDto>> getSkillsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(skillService.getSkillsByCategory(categoryId));
    }
    
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @Operation(summary = "Создать навык", description = "Создать новый навык (только ADMIN/MODERATOR)")
    public ResponseEntity<SkillDto> createSkill(@RequestBody SkillDto skillDto) {
        return ResponseEntity.ok(skillService.createSkill(skillDto));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @Operation(summary = "Обновить навык", description = "Обновить информацию о навыке (только ADMIN/MODERATOR)")
    public ResponseEntity<SkillDto> updateSkill(@PathVariable Long id, @RequestBody SkillDto skillDto) {
        return ResponseEntity.ok(skillService.updateSkill(id, skillDto));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @Operation(summary = "Удалить навык", description = "Удалить навык по ID (только ADMIN/MODERATOR)")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return ResponseEntity.ok().build();
    }
}
