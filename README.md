# Skill Matrix Management System

Система управления матрицей навыков сотрудников, построенная на Spring Boot 3 с Java 21 и PostgreSQL.

## 🚀 Технологии

- **Java 21**
- **Spring Boot 3.5.4**
- **Spring Security** с JWT аутентификацией
- **Spring Data JPA**
- **PostgreSQL 15**
- **Swagger/OpenAPI 3**
- **Docker & Docker Compose**
- **Lombok**
- **Maven**

## 📋 Функциональность

### 👨‍💼 Администратор
- **Управление пользователями**
  - Просмотр списка сотрудников
  - Просмотр детальной информации о сотруднике
  - Добавление новых сотрудников
  - Редактирование информации о сотрудниках
  - Удаление сотрудников
  - Редактирование баллов за навыки

- **Управление критериями**
  - Просмотр списка критериев
  - Создание, редактирование, удаление категорий
  - Создание, редактирование, удаление навыков
  - Управление максимальными баллами за навыки
  - Настройка обязательности навыков для должностей
  - Управление описаниями навыков

- **Управление распределением баллов**
  - Просмотр распределения
  - Настройка минимальных требований по soft/hard баллам для должностей

### 👨‍💻 Модератор
- Все функции администратора, кроме удаления пользователей

### 👤 Пользователь
- Регистрация и авторизация
- Просмотр личной информации
- Просмотр своих баллов по навыкам
- Просмотр категорий и навыков с описаниями

## 🏗️ Архитектура

```
src/main/java/com/example/skillmatrix/
├── config/          # Конфигурации (Security, OpenAPI)
├── controller/      # REST контроллеры
├── dto/            # Data Transfer Objects
├── model/          # JPA сущности
├── repository/     # Spring Data репозитории
└── service/        # Бизнес-логика
```

## 🚀 Запуск проекта

### С помощью Docker Compose (рекомендуется)

1. Клонируйте репозиторий:
```bash
git clone <repository-url>
cd skill-matrix
```

2. Запустите приложение:
```bash
docker-compose up -d
```

3. Приложение будет доступно по адресу: http://localhost:8080

### Локальный запуск

1. Установите Java 21 и Maven

2. Установите и запустите PostgreSQL:
```bash
# Создайте базу данных
createdb skill_matrix

# Или используйте Docker:
docker run --name postgres-skill-matrix -e POSTGRES_DB=skill_matrix -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=1234 -p 5433:5432 -d postgres:15-alpine
```

3. Обновите настройки в `src/main/resources/application.properties`

4. Запустите приложение:
```bash
./mvnw spring-boot:run
```

## 📚 API Документация

После запуска приложения API документация доступна по адресам:
- **JSON API:** http://localhost:8080/api/docs
- **HTML документация:** http://localhost:8080/api-docs.html

Документация содержит:
- Список всех эндпоинтов
- Описание ролей и прав доступа
- Примеры запросов и ответов
- Информацию об аутентификации
- Интерактивную HTML страницу с примерами

## 🔐 Аутентификация

API использует JWT токены для аутентификации. Для доступа к защищенным эндпоинтам добавьте заголовок:
```
Authorization: Bearer <your-jwt-token>
```

### Получение токена:
```bash
# Регистрация
POST /api/auth/register
{
  "username": "user",
  "email": "user@example.com",
  "firstName": "Иван",
  "lastName": "Иванов",
  "direction": "Разработка",
  "currentPosition": "Junior Developer"
}

# Авторизация
POST /api/auth/login
{
  "username": "user",
  "password": "password"
}
```

## 🗄️ База данных

### Основные сущности:

- **User** - пользователи системы
- **Category** - категории навыков
- **Skill** - навыки с типами (HARD/SOFT)
- **UserSkill** - связь пользователей и навыков с баллами
- **Position** - должности с требованиями
- **PositionSkill** - требования должностей к навыкам

## 🔧 Конфигурация

Основные настройки в `application.properties`:

```properties
# База данных
spring.datasource.url=jdbc:postgresql://localhost:5432/skill_matrix
spring.datasource.username=postgres
spring.datasource.password=password

# JWT
jwt.secret=your-secret-key
jwt.expiration=86400000

# Swagger
springdoc.swagger-ui.path=/swagger-ui.html
```

## 🧪 Тестирование

Запуск тестов:
```bash
./mvnw test
```

## 📦 Сборка

Создание JAR файла:
```bash
./mvnw clean package
```

## 🐳 Docker

### Сборка образа:
```bash
docker build -t skill-matrix .
```

### Запуск контейнера:
```bash
docker run -p 8080:8080 skill-matrix
```

## 📝 Примеры использования

### Создание категории:
```bash
curl -X POST "http://localhost:8080/api/categories" \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Программирование",
    "description": "Навыки программирования"
  }'
```

### Создание навыка:
```bash
curl -X POST "http://localhost:8080/api/skills" \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Java",
    "description": "Программирование на Java",
    "type": "HARD",
    "categoryId": 1,
    "maxPoints": 5
  }'
```

### Обновление баллов пользователя:
```bash
curl -X PUT "http://localhost:8080/api/users/1/skills/1?points=4" \
  -H "Authorization: Bearer <token>"
```

## 🤝 Вклад в проект

1. Форкните репозиторий
2. Создайте ветку для новой функции
3. Внесите изменения
4. Создайте Pull Request

## 📄 Лицензия

MIT License

## 📞 Поддержка

По вопросам обращайтесь: support@skillmatrix.com
