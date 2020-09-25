CREATE TABLE IF NOT EXISTS senha (
	id SERIAL NOT NULL PRIMARY KEY,
	data_criacao TIMESTAMP NOT NULL,
	valor VARCHAR(6) NOT NULL,
	chamado boolean not null default false
);