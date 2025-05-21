CREATE TABLE medico(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL,
	telefone BIGINT NOT NULL,
	crm VARCHAR(20) NOT NULL,
	especialidade VARCHAR(20) NOT NULL,
	logradouro VARCHAR(50) NOT NULL,
	numero INTEGER,
	bairro VARCHAR(30) NOT NULL,
	complemento VARCHAR(30),
	cidade VARCHAR(30) NOT NULL
	ativo BOOLEAN DEFAULT TRUE
);

CREATE TABLE paciente(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone BIGINT NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    logradouro VARCHAR(50) NOT NULL,
    numero INTEGER,
    complemento VARCHAR(30),
    bairro VARCHAR(30) NOT NULL,
    cidade VARCHAR(30) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    cep BIGINT NOT NULL,
    ativo BOOLEAN DEFAULT TRUE
);

CREATE TABLE consulta(
    id SERIAL PRIMARY KEY,
    medico_id INTEGER NOT NULL,
    paciente_id INTEGER NOT NULL,
    data_hora TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    ativo BOOLEAN DEFAULT true
);

-- Utilizar: YYYY-MM-DDThh:mm:ss para data/hora.
