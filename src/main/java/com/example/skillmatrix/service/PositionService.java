package com.example.skillmatrix.service;

import com.example.skillmatrix.dto.PositionDto;
import com.example.skillmatrix.model.Position;
import com.example.skillmatrix.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PositionService {
    
    private final PositionRepository positionRepository;
    
    public List<PositionDto> getAllPositions() {
        return positionRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public PositionDto getPositionById(Long id) {
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Position not found with id: " + id));
        return convertToDto(position);
    }
    
    public PositionDto createPosition(PositionDto positionDto) {
        if (positionRepository.existsByName(positionDto.getName())) {
            throw new RuntimeException("Position with name " + positionDto.getName() + " already exists");
        }
        
        Position position = new Position();
        position.setName(positionDto.getName());
        position.setMinHardPoints(positionDto.getMinHardPoints() != null ? positionDto.getMinHardPoints() : 0);
        position.setMinSoftPoints(positionDto.getMinSoftPoints() != null ? positionDto.getMinSoftPoints() : 0);
        position.setDescription(positionDto.getDescription());
        
        Position savedPosition = positionRepository.save(position);
        return convertToDto(savedPosition);
    }
    
    public PositionDto updatePosition(Long id, PositionDto positionDto) {
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Position not found with id: " + id));
        
        position.setName(positionDto.getName());
        position.setMinHardPoints(positionDto.getMinHardPoints() != null ? positionDto.getMinHardPoints() : 0);
        position.setMinSoftPoints(positionDto.getMinSoftPoints() != null ? positionDto.getMinSoftPoints() : 0);
        position.setDescription(positionDto.getDescription());
        
        Position savedPosition = positionRepository.save(position);
        return convertToDto(savedPosition);
    }
    
    public void deletePosition(Long id) {
        positionRepository.deleteById(id);
    }
    
    private PositionDto convertToDto(Position position) {
        PositionDto dto = new PositionDto();
        dto.setId(position.getId());
        dto.setName(position.getName());
        dto.setMinHardPoints(position.getMinHardPoints());
        dto.setMinSoftPoints(position.getMinSoftPoints());
        dto.setDescription(position.getDescription());
        return dto;
    }
}
