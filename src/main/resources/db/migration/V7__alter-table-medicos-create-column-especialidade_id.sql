alter table medicos
    drop column especialidade,
    add column especialidade_id bigint,
    add constraint fk_medicos_especialidade_id foreign key(especialidade_id)
    references especialidades(id);