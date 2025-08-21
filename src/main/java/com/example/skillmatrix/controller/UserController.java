package com.example.skillmatrix.controller;

import com.example.skillmatrix.dto.UserDto;
import com.example.skillmatrix.dto.UserSkillDto;
import com.example.skillmatrix.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "API для управления пользователями")
public class UserController {
    
    private final UserService userService;
    
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @Operation(summary = "Получить всех пользователей", description = "Получить список всех пользователей (только ADMIN/MODERATOR)")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR') or #id == authentication.principal.id")
    @Operation(summary = "Получить пользователя по ID", description = "Получить детальную информацию о пользователе")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
    
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @Operation(summary = "Создать пользователя", description = "Создать нового пользователя (только ADMIN/MODERATOR)")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR') or #id == authentication.principal.id")
    @Operation(summary = "Обновить пользователя", description = "Обновить информацию о пользователе")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Удалить пользователя", description = "Удалить пользователя по ID (только ADMIN)")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}/skills")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR') or #id == authentication.principal.id")
    @Operation(summary = "Получить навыки пользователя", description = "Получить все навыки конкретного пользователя")
    public ResponseEntity<List<UserSkillDto>> getUserSkills(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserSkills(id));
    }
    
    @PutMapping("/{userId}/skills/{skillId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Обновить навык пользователя", description = "Обновить баллы за конкретный навык пользователя")
    public ResponseEntity<UserSkillDto> updateUserSkill(
            @PathVariable Long userId,
            @PathVariable Long skillId,
            @RequestParam Integer points) {
        return ResponseEntity.ok(userService.updateUserSkill(userId, skillId, points));
    }
}
