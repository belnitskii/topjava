DELETE FROM user_role;
DELETE FROM users;
DELETE
FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES (TIMESTAMP '2026-02-21 08:00:00', 'breakfast', 500, 100000),
       (TIMESTAMP '2026-02-21 12:00:00', 'lunch', 1000, 100000),
       (TIMESTAMP '2026-02-21 18:00:00', 'dinner', 500, 100000),
       (TIMESTAMP '2026-02-22 00:00:00', 'meal on borderline cases', 10, 100000),
       (TIMESTAMP '2026-02-22 10:00:00', 'breakfast', 490, 100000),
       (TIMESTAMP '2026-02-22 13:00:00', 'lunch', 1000, 100000),
       (TIMESTAMP '2026-02-22 22:00:00', 'dinner', 500, 100000),
       (TIMESTAMP '2026-02-21 08:00:00', 'breakfast', 500, 100001),
       (TIMESTAMP '2026-02-22 12:00:00', 'lunch', 1000, 100001);