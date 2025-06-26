create table usuarios (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    login varchar(100) not null unique,
    senha varchar(255) not null,
    role varchar(50) not null
);