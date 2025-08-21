# Quick Start Guide

## Быстрый запуск с Docker

1. **Клонируйте репозиторий:**
```bash
git clone <repository-url>
cd skill-matrix
```

2. **Запустите приложение:**
```bash
docker-compose up -d
```

3. **Откройте API документацию:**
http://localhost:8080/api/docs

## Тестовые пользователи

После запуска в системе будут созданы следующие пользователи:

| Username | Password | Role | Описание |
|----------|----------|------|----------|
| admin | password | ADMIN | Администратор системы |
| moderator | password | MODERATOR | Модератор |
| user1 | password | USER | Обычный пользователь |
| user2 | password | USER | Обычный пользователь |

**Примечание:** Все пользователи создаются с паролем "password" по умолчанию.

## Первые шаги

1. **Авторизуйтесь как admin:**
```bash
curl -X POST "http://localhost:8080/api/auth/login" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "password"
  }'
```

2. **Используйте полученный токен для доступа к API:**
```bash
curl -X GET "http://localhost:8080/api/users" \
  -H "Authorization: Bearer <your-jwt-token>"
```

## Основные эндпоинты

- **Аутентификация:** `/api/auth/*`
- **Пользователи:** `/api/users/*`
- **Категории:** `/api/categories/*`
- **Навыки:** `/api/skills/*`
- **Должности:** `/api/positions/*`

## Остановка приложения

```bash
docker-compose down
```

Для удаления всех данных:
```bash
docker-compose down -v
```
