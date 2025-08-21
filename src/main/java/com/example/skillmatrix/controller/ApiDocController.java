package com.example.skillmatrix.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/docs")
public class ApiDocController {

    @GetMapping
    public ResponseEntity<Map<String, Object>> getApiDocumentation() {
        Map<String, Object> documentation = new HashMap<>();
        
        documentation.put("title", "Skill Matrix API Documentation");
        documentation.put("version", "1.0");
        documentation.put("description", "API для управления матрицей навыков сотрудников");
        
        Map<String, Object> endpoints = new HashMap<>();
        
        // Authentication endpoints
        Map<String, Object> auth = new HashMap<>();
        auth.put("POST /api/auth/register", "Регистрация нового пользователя");
        auth.put("POST /api/auth/login", "Авторизация пользователя");
        endpoints.put("Authentication", auth);
        
        // User endpoints
        Map<String, Object> users = new HashMap<>();
        users.put("GET /api/users", "Получить всех пользователей (ADMIN/MODERATOR)");
        users.put("GET /api/users/{id}", "Получить пользователя по ID");
        users.put("POST /api/users", "Создать пользователя (ADMIN/MODERATOR)");
        users.put("PUT /api/users/{id}", "Обновить пользователя");
        users.put("DELETE /api/users/{id}", "Удалить пользователя (ADMIN)");
        users.put("GET /api/users/{id}/skills", "Получить навыки пользователя");
        users.put("PUT /api/users/{userId}/skills/{skillId}", "Обновить баллы навыка (ADMIN/MODERATOR)");
        endpoints.put("Users", users);
        
        // Category endpoints
        Map<String, Object> categories = new HashMap<>();
        categories.put("GET /api/categories", "Получить все категории");
        categories.put("GET /api/categories/{id}", "Получить категорию по ID");
        categories.put("POST /api/categories", "Создать категорию (ADMIN/MODERATOR)");
        categories.put("PUT /api/categories/{id}", "Обновить категорию (ADMIN/MODERATOR)");
        categories.put("DELETE /api/categories/{id}", "Удалить категорию (ADMIN/MODERATOR)");
        endpoints.put("Categories", categories);
        
        // Skill endpoints
        Map<String, Object> skills = new HashMap<>();
        skills.put("GET /api/skills", "Получить все навыки");
        skills.put("GET /api/skills/{id}", "Получить навык по ID");
        skills.put("GET /api/skills/category/{categoryId}", "Получить навыки по категории");
        skills.put("POST /api/skills", "Создать навык (ADMIN/MODERATOR)");
        skills.put("PUT /api/skills/{id}", "Обновить навык (ADMIN/MODERATOR)");
        skills.put("DELETE /api/skills/{id}", "Удалить навык (ADMIN/MODERATOR)");
        endpoints.put("Skills", skills);
        
        // Position endpoints
        Map<String, Object> positions = new HashMap<>();
        positions.put("GET /api/positions", "Получить все должности");
        positions.put("GET /api/positions/{id}", "Получить должность по ID");
        positions.put("POST /api/positions", "Создать должность (ADMIN/MODERATOR)");
        positions.put("PUT /api/positions/{id}", "Обновить должность (ADMIN/MODERATOR)");
        positions.put("DELETE /api/positions/{id}", "Удалить должность (ADMIN/MODERATOR)");
        endpoints.put("Positions", positions);
        
        documentation.put("endpoints", endpoints);
        
        Map<String, Object> authentication = new HashMap<>();
        authentication.put("type", "JWT Bearer Token");
        authentication.put("header", "Authorization: Bearer <your-jwt-token>");
        authentication.put("note", "Получите токен через /api/auth/login");
        documentation.put("authentication", authentication);
        
        Map<String, Object> roles = new HashMap<>();
        roles.put("ADMIN", "Полный доступ ко всем функциям");
        roles.put("MODERATOR", "Доступ ко всем функциям, кроме удаления пользователей");
        roles.put("USER", "Доступ только к своим данным и просмотру категорий/навыков");
        documentation.put("roles", roles);
        
        Map<String, Object> examples = new HashMap<>();
        
        Map<String, Object> loginExample = new HashMap<>();
        loginExample.put("url", "POST /api/auth/login");
        loginExample.put("body", "{\"username\": \"admin\", \"password\": \"password\"}");
        loginExample.put("response", "{\"token\": \"jwt-token\", \"username\": \"admin\", \"role\": \"ADMIN\"}");
        examples.put("Login", loginExample);
        
        Map<String, Object> createUserExample = new HashMap<>();
        createUserExample.put("url", "POST /api/users");
        createUserExample.put("body", "{\"username\": \"newuser\", \"email\": \"user@example.com\", \"firstName\": \"Иван\", \"lastName\": \"Иванов\", \"direction\": \"Разработка\", \"currentPosition\": \"Junior Developer\"}");
        examples.put("Create User", createUserExample);
        
        documentation.put("examples", examples);
        
        return ResponseEntity.ok(documentation);
    }
}
