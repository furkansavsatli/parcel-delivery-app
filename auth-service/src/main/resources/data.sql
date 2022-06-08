INSERT INTO roles(id, name)
VALUES (1, 'ROLE_USER');
INSERT INTO roles(id, name)
VALUES (2, 'ROLE_ADMIN');
INSERT INTO roles(id, name)
VALUES (3, 'ROLE_COURIER');

-- user pass 12345678
INSERT INTO users (id, email, name, password)
VALUES (1000, 'user@gmail.com', 'user', '$2a$10$Uk6ESOOrW0FZQ1llPN71XOGyc0izXEeG7uJ2xTBhFpgDubpaXRk0K');

INSERT INTO user_roles (user_id, role_id)
VALUES (1000, 1);

-- admin
INSERT INTO users (id, email, name, password)
VALUES (2000, 'admin@gmail.com', 'admin', '$2a$10$Uk6ESOOrW0FZQ1llPN71XOGyc0izXEeG7uJ2xTBhFpgDubpaXRk0K');

INSERT INTO user_roles (user_id, role_id)
VALUES (2000, 2);

-- courier
INSERT INTO users (id, email, name, password)
VALUES (3000, 'courier@gmail.com', 'courier', '$2a$10$Uk6ESOOrW0FZQ1llPN71XOGyc0izXEeG7uJ2xTBhFpgDubpaXRk0K');

INSERT INTO user_roles (user_id, role_id)
VALUES (3000, 3);