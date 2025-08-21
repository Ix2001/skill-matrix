# HTTP Error Codes Documentation

## Обзор кодов ошибок

Система Skill Matrix возвращает стандартные HTTP коды ошибок с детальными сообщениями.

## Коды ошибок

### 400 - Bad Request
**Описание:** Неверный запрос или ошибка валидации

**Примеры:**
```json
{
  "status": 400,
  "error": "Validation Error",
  "message": "Validation failed for the following fields: {username=Username is required, email=Email should be valid}",
  "timestamp": "2024-01-15T10:30:00",
  "details": {
    "username": "Username is required",
    "email": "Email should be valid"
  }
}
```

**Причины:**
- Отсутствуют обязательные поля
- Неверный формат данных
- Нарушение ограничений валидации (размер, формат и т.д.)

### 401 - Unauthorized
**Описание:** Не авторизован

**Пример:**
```json
{
  "status": 401,
  "error": "Authentication Failed",
  "message": "Invalid username or password",
  "timestamp": "2024-01-15T10:30:00"
}
```

**Причины:**
- Отсутствует JWT токен
- Неверные учетные данные
- Истекший токен

### 403 - Forbidden
**Описание:** Доступ запрещен

**Пример:**
```json
{
  "status": 403,
  "error": "Access Denied",
  "message": "You don't have permission to access this resource",
  "timestamp": "2024-01-15T10:30:00"
}
```

**Причины:**
- Недостаточно прав для выполнения операции
- Попытка доступа к ресурсу другого пользователя

### 404 - Not Found
**Описание:** Ресурс не найден

**Пример:**
```json
{
  "status": 404,
  "error": "Resource Not Found",
  "message": "User not found with id : '999'",
  "timestamp": "2024-01-15T10:30:00"
}
```

**Причины:**
- Запрашиваемый ресурс не существует
- Неверный ID в URL

### 409 - Conflict
**Описание:** Конфликт ресурсов

**Пример:**
```json
{
  "status": 409,
  "error": "Resource Already Exists",
  "message": "User already exists with username : 'john_doe'",
  "timestamp": "2024-01-15T10:30:00"
}
```

**Причины:**
- Попытка создать ресурс с уже существующим уникальным значением
- Нарушение уникальности

### 500 - Internal Server Error
**Описание:** Внутренняя ошибка сервера

**Пример:**
```json
{
  "status": 500,
  "error": "Internal Server Error",
  "message": "An unexpected error occurred",
  "timestamp": "2024-01-15T10:30:00"
}
```

**Причины:**
- Неожиданная ошибка в приложении
- Ошибка базы данных
- Системная ошибка

## Специфичные ошибки валидации

### Ошибки бизнес-логики
```json
{
  "status": 400,
  "error": "Validation Error",
  "message": "Points cannot exceed maximum allowed points (5) for skill 'Java'",
  "timestamp": "2024-01-15T10:30:00"
}
```

### Ошибки удаления
```json
{
  "status": 400,
  "error": "Validation Error",
  "message": "Cannot delete category with 3 skills. Remove all skills first.",
  "timestamp": "2024-01-15T10:30:00"
}
```

## Обработка ошибок в клиенте

### JavaScript/TypeScript
```javascript
async function makeRequest(url, options) {
  try {
    const response = await fetch(url, options);
    
    if (!response.ok) {
      const errorData = await response.json();
      throw new Error(`${errorData.error}: ${errorData.message}`);
    }
    
    return await response.json();
  } catch (error) {
    console.error('Request failed:', error);
    throw error;
  }
}
```

### cURL
```bash
# Пример запроса с обработкой ошибок
curl -X POST "http://localhost:8080/api/auth/login" \
  -H "Content-Type: application/json" \
  -d '{"username": "invalid", "password": "wrong"}' \
  -w "\nHTTP Status: %{http_code}\n"
```

## Логирование ошибок

Все ошибки логируются на сервере с уровнем ERROR и включают:
- Время возникновения
- Тип ошибки
- Детали запроса
- Stack trace (для 500 ошибок)

## Рекомендации

1. **Всегда проверяйте HTTP статус** перед обработкой ответа
2. **Используйте детали ошибок** для улучшения UX
3. **Логируйте ошибки** на клиенте для отладки
4. **Обрабатывайте сетевые ошибки** отдельно от бизнес-ошибок
