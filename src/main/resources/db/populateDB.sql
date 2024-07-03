DELETE FROM user_role;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('2020-01-30 10:00:00', 'Завтрак', 500, 100000),
       ('2020-01-30 13:00:00', 'Обед', 1000, 100000),
       ('2020-01-30 20:00:00', 'Ужин', 500, 100000),
       ('2020-01-31 00:00:00', 'Еда на пограничное значение', 100, 100000),
       ('2020-01-31 10:00:00', 'Завтрак', 1000, 100000),
       ('2020-01-31 13:00:00', 'Обеда', 500, 100000),
       ('2020-01-31 20:00:00', 'Ужин', 410, 100000),
       ('2020-01-31 07:00:00', 'Admin breakfast', 410, 100000),
       ('2020-01-31 19:30:00', 'Admin dinner', 410, 100000);
