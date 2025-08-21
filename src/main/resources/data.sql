-- Создание категорий
INSERT INTO categories (name, description) VALUES 
('Программирование', 'Навыки программирования и разработки'),
('Базы данных', 'Работа с базами данных'),
('DevOps', 'DevOps практики и инструменты'),
('Soft Skills', 'Мягкие навыки и коммуникация'),
('Архитектура', 'Архитектурные решения и паттерны');

-- Создание навыков
INSERT INTO skills (name, description, type, category_id, max_points) VALUES 
('Java', 'Программирование на Java', 'HARD', 1, 5),
('Spring Boot', 'Разработка на Spring Boot', 'HARD', 1, 5),
('Python', 'Программирование на Python', 'HARD', 1, 5),
('JavaScript', 'Программирование на JavaScript', 'HARD', 1, 5),
('PostgreSQL', 'Работа с PostgreSQL', 'HARD', 2, 5),
('MySQL', 'Работа с MySQL', 'HARD', 2, 5),
('Docker', 'Контейнеризация с Docker', 'HARD', 3, 5),
('Kubernetes', 'Оркестрация с Kubernetes', 'HARD', 3, 5),
('Коммуникация', 'Навыки коммуникации', 'SOFT', 4, 5),
('Командная работа', 'Работа в команде', 'SOFT', 4, 5),
('Микросервисы', 'Архитектура микросервисов', 'HARD', 5, 5),
('REST API', 'Разработка REST API', 'HARD', 5, 5);

-- Создание должностей
INSERT INTO positions (name, min_hard_points, min_soft_points, description) VALUES 
('Junior Developer', 10, 5, 'Младший разработчик'),
('Middle Developer', 20, 10, 'Средний разработчик'),
('Senior Developer', 30, 15, 'Старший разработчик'),
('Team Lead', 25, 20, 'Руководитель команды'),
('Architect', 35, 15, 'Архитектор');

-- Создание пользователей (пароль: password)
INSERT INTO users (username, password, email, first_name, last_name, direction, current_position, role) VALUES 
('admin', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'admin@skillmatrix.com', 'Администратор', 'Системы', 'IT', 'Admin', 'ADMIN'),
('moderator', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'moderator@skillmatrix.com', 'Модератор', 'Системы', 'IT', 'Moderator', 'MODERATOR'),
('user1', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user1@skillmatrix.com', 'Иван', 'Иванов', 'Разработка', 'Junior Developer', 'USER'),
('user2', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user2@skillmatrix.com', 'Петр', 'Петров', 'Разработка', 'Middle Developer', 'USER');
