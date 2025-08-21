# Устранение неполадок

## Проблемы с запуском

### 1. Ошибка SpringDoc OpenAPI
**Симптомы:** `NoSuchMethodError: 'void org.springframework.web.method.ControllerAdviceBean.<init>(java.lang.Object)'`

**Решение:**
- Обновите версию SpringDoc до 2.4.0 или выше
- Убедитесь, что используется совместимая версия Spring Boot

### 2. Ошибки подключения к базе данных
**Симптомы:** `Connection refused` или `Authentication failed`

**Решение:**
```bash
# Проверьте, что PostgreSQL запущен
ps aux | grep postgres

# Проверьте настройки подключения в application.properties
spring.datasource.url=jdbc:postgresql://localhost:5433/skill_matrix
spring.datasource.username=postgres
spring.datasource.password=1234
```

### 3. Ошибки JWT
**Симптомы:** `Invalid JWT token` или `Signature verification failed`

**Решение:**
- Убедитесь, что JWT секрет правильно настроен
- Проверьте формат секрета (должен быть base64 или обычная строка)

## Проблемы с валидацией

### 1. Ошибки 400 Bad Request
**Причины:**
- Неверный формат данных
- Отсутствуют обязательные поля
- Нарушение ограничений валидации

**Решение:**
- Проверьте формат JSON
- Убедитесь, что все обязательные поля заполнены
- Проверьте ограничения (размер строк, диапазоны чисел)

### 2. Ошибки 401 Unauthorized
**Причины:**
- Отсутствует JWT токен
- Неверные учетные данные
- Истекший токен

**Решение:**
```bash
# Получите новый токен
curl -X POST "http://localhost:8080/api/auth/login" \
  -H "Content-Type: application/json" \
  -d '{"username": "admin", "password": "password"}'
```

### 3. Ошибки 403 Forbidden
**Причины:**
- Недостаточно прав для выполнения операции
- Попытка доступа к ресурсу другого пользователя

**Решение:**
- Убедитесь, что у пользователя есть нужная роль
- Проверьте права доступа в контроллерах

## Проблемы с Docker

### 1. Контейнер не запускается
**Решение:**
```bash
# Проверьте логи
docker-compose logs app

# Пересоберите образ
docker-compose build --no-cache

# Удалите старые контейнеры и тома
docker-compose down -v
docker-compose up -d
```

### 2. Проблемы с сетью
**Решение:**
```bash
# Проверьте сеть
docker network ls

# Создайте сеть заново
docker network create skill-matrix-network
```

## Проблемы с производительностью

### 1. Медленные запросы
**Решение:**
- Проверьте индексы в базе данных
- Включите кэширование
- Оптимизируйте запросы

### 2. Утечки памяти
**Решение:**
- Проверьте логи на наличие ошибок
- Мониторьте использование памяти
- Перезапустите приложение при необходимости

## Логирование

### Включение подробного логирования
```properties
# application.properties
logging.level.com.example.skillmatrix=DEBUG
logging.level.org.springframework.security=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

### Просмотр логов
```bash
# В Docker
docker-compose logs -f app

# Локально
tail -f logs/application.log
```

## Тестирование API

### Проверка здоровья приложения
```bash
curl http://localhost:8080/actuator/health
```

### Проверка Swagger UI
```bash
# Откройте в браузере
http://localhost:8080/swagger-ui.html
```

### Тестирование аутентификации
```bash
# Регистрация
curl -X POST "http://localhost:8080/api/auth/register" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "firstName": "Test",
    "lastName": "User",
    "direction": "Development",
    "currentPosition": "Developer"
  }'

# Авторизация
curl -X POST "http://localhost:8080/api/auth/login" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "password"
  }'
```

## Контакты

Если проблемы не решаются, обратитесь:
- Email: support@skillmatrix.com
- GitHub Issues: [Создать issue](https://github.com/your-repo/issues)
