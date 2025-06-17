CREATE TABLE prescricoes (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    medicamento TEXT NOT NULL,
    posologia varchar(255),
    observacoes varchar(255),
    criado_em TIMESTAMP,
    consulta_id BIGINT NOT NULL,
    CONSTRAINT fk_prescricao_consulta FOREIGN KEY (consulta_id) REFERENCES consultas(id) ON DELETE CASCADE
);
