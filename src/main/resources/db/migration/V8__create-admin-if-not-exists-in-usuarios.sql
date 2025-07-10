INSERT INTO usuarios (login, senha, role)
SELECT
    'admin@sghss.com',
    '$2a$12$biftuR/GI7vpkb4aue5IgOWMXXaHQXtRF08HuCP6LylZtX86bnWLC', -- senha: admin
    'ADMIN'
WHERE NOT EXISTS (
    SELECT 1 FROM usuarios WHERE role = 'ADMIN'
);