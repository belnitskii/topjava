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
VALUES (TIMESTAMP '2026-02-23 08:00:00', 'breakfast', 600, 100000),
       (TIMESTAMP '2026-02-23 12:00:00', 'lunch', 1200, 100000),
       (TIMESTAMP '2026-02-23 18:20:35', 'dinner', 900, 100000),
       (TIMESTAMP '2026-02-23 12:00:00', 'coffee', 140, 100001),
       (TIMESTAMP '2026-02-23 12:15:17', 'gyros', 1000, 100001);