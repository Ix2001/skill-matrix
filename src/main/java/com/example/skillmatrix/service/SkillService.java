package com.example.skillmatrix.service;

import com.example.skillmatrix.dto.SkillDto;
import com.example.skillmatrix.model.Category;
import com.example.skillmatrix.model.Skill;
import com.example.skillmatrix.repository.CategoryRepository;
import com.example.skillmatrix.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillService {
    
    private final SkillRepository skillRepository;
    private final CategoryRepository categoryRepository;
    
    public List<SkillDto> getAllSkills() {
        return skillRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public SkillDto getSkillById(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found with id: " + id));
        return convertToDto(skill);
    }
    
    public List<SkillDto> getSkillsByCategory(Long categoryId) {
        return skillRepository.findByCategoryId(categoryId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public SkillDto createSkill(SkillDto skillDto) {
        Category category = categoryRepository.findById(skillDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + skillDto.getCategoryId()));
        
        if (skillRepository.existsByNameAndCategoryId(skillDto.getName(), skillDto.getCategoryId())) {
            throw new RuntimeException("Skill with name " + skillDto.getName() + " already exists in this category");
        }
        
        Skill skill = new Skill();
        skill.setName(skillDto.getName());
        skill.setDescription(skillDto.getDescription());
        skill.setType(skillDto.getType());
        skill.setCategory(category);
        skill.setMaxPoints(skillDto.getMaxPoints() != null ? skillDto.getMaxPoints() : 1);
        
        Skill savedSkill = skillRepository.save(skill);
        return convertToDto(savedSkill);
    }
    
    public SkillDto updateSkill(Long id, SkillDto skillDto) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found with id: " + id));
        
        Category category = categoryRepository.findById(skillDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + skillDto.getCategoryId()));
        
        skill.setName(skillDto.getName());
        skill.setDescription(skillDto.getDescription());
        skill.setType(skillDto.getType());
        skill.setCategory(category);
        skill.setMaxPoints(skillDto.getMaxPoints() != null ? skillDto.getMaxPoints() : 1);
        
        Skill savedSkill = skillRepository.save(skill);
        return convertToDto(savedSkill);
    }
    
    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }
    
    private SkillDto convertToDto(Skill skill) {
        SkillDto dto = new SkillDto();
        dto.setId(skill.getId());
        dto.setName(skill.getName());
        dto.setDescription(skill.getDescription());
        dto.setType(skill.getType());
        dto.setCategoryId(skill.getCategory().getId());
        dto.setMaxPoints(skill.getMaxPoints());
        return dto;
    }
}
