package com.example.skillmatrix.service;

import com.example.skillmatrix.dto.UserDto;
import com.example.skillmatrix.dto.UserSkillDto;
import com.example.skillmatrix.model.User;
import com.example.skillmatrix.model.UserSkill;
import com.example.skillmatrix.repository.UserRepository;
import com.example.skillmatrix.repository.UserSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    
    private final UserRepository userRepository;
    private final UserSkillRepository userSkillRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
    
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return convertToDto(user);
    }
    
    public UserDto createUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new RuntimeException("User with username " + userDto.getUsername() + " already exists");
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("User with email " + userDto.getEmail() + " already exists");
        }
        
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode("password")); // Default password
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setDirection(userDto.getDirection());
        user.setCurrentPosition(userDto.getCurrentPosition());
        user.setExpectedPosition(userDto.getExpectedPosition());
        user.setRole(userDto.getRole() != null ? userDto.getRole() : User.Role.USER);
        
        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }
    
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setDirection(userDto.getDirection());
        user.setCurrentPosition(userDto.getCurrentPosition());
        user.setExpectedPosition(userDto.getExpectedPosition());
        
        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }
    
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    public List<UserSkillDto> getUserSkills(Long userId) {
        return userSkillRepository.findByUserId(userId).stream()
                .map(this::convertToUserSkillDto)
                .collect(Collectors.toList());
    }
    
    public UserSkillDto updateUserSkill(Long userId, Long skillId, Integer points) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        
        UserSkill userSkill = userSkillRepository.findByUserIdAndSkillId(userId, skillId)
                .orElseGet(() -> {
                    UserSkill newUserSkill = new UserSkill();
                    newUserSkill.setUser(user);
                    newUserSkill.setSkill(userSkillRepository.findById(skillId)
                            .orElseThrow(() -> new RuntimeException("UserSkill not found with id: " + skillId))
                            .getSkill());
                    newUserSkill.setPoints(points);
                    return newUserSkill;
                });
        
        userSkill.setPoints(points);
        UserSkill savedUserSkill = userSkillRepository.save(userSkill);
        return convertToUserSkillDto(savedUserSkill);
    }
    
    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setDirection(user.getDirection());
        dto.setCurrentPosition(user.getCurrentPosition());
        dto.setExpectedPosition(user.getExpectedPosition());
        dto.setRole(user.getRole());
        return dto;
    }
    
    private UserSkillDto convertToUserSkillDto(UserSkill userSkill) {
        UserSkillDto dto = new UserSkillDto();
        dto.setId(userSkill.getId());
        dto.setUserId(userSkill.getUser().getId());
        dto.setSkillId(userSkill.getSkill().getId());
        dto.setPoints(userSkill.getPoints());
        dto.setSkillName(userSkill.getSkill().getName());
        dto.setSkillType(userSkill.getSkill().getType().name());
        dto.setMaxPoints(userSkill.getSkill().getMaxPoints());
        return dto;
    }
}
