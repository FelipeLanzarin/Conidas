CREATE TABLE cidade
(
  id integer NOT NULL,
  name character varying(255),
  pais character varying(255),
  uf character varying(255),
  CONSTRAINT cidade_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cidade
  OWNER TO convidas;

  -- Table: pessoa_fisica

-- DROP TABLE pessoa_fisica;

CREATE TABLE pessoa_fisica
(
  id integer NOT NULL,
  bairro character varying(255),
  cep character varying(255),
  cpf character varying(255),
  email character varying(255),
  historico character varying(9999),
  name character varying(255),
  newsletter boolean,
  numero character varying(255),
  relacao character varying(255),
  rua character varying(255),
  telefone character varying(255),
  cidade_id integer,
  CONSTRAINT pessoa_fisica_pkey PRIMARY KEY (id),
  CONSTRAINT fk_18gefi2v6pbmbt3iof3q350y FOREIGN KEY (cidade_id)
      REFERENCES cidade (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pessoa_fisica
  OWNER TO convidas;

  -- Table: pessoa_juridica

-- DROP TABLE pessoa_juridica;

CREATE TABLE pessoa_juridica
(
  id integer NOT NULL,
  bairro character varying(255),
  cep character varying(255),
  cnpj character varying(255),
  contribuicao character varying(255),
  email character varying(255),
  historico character varying(9999),
  name character varying(255),
  newsletter boolean,
  numero character varying(255),
  responsavel character varying(255),
  rua character varying(255),
  telefone character varying(9999),
  cidade_id integer,
  CONSTRAINT pessoa_juridica_pkey PRIMARY KEY (id),
  CONSTRAINT fk_a375jkka2nglvjrnu0qrjg80e FOREIGN KEY (cidade_id)
      REFERENCES cidade (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pessoa_juridica
  OWNER TO convidas;

  
  
 -- Table: ocorrency_pf

-- DROP TABLE ocorrency_pf;

CREATE TABLE ocorrency_pf
(
  id integer NOT NULL,
  date timestamp without time zone,
  description character varying(9999),
  pf_id integer,
  CONSTRAINT ocorrency_pf_pkey PRIMARY KEY (id),
  CONSTRAINT fk_simvw53xqfj1h4vu84w0edc4w FOREIGN KEY (pf_id)
      REFERENCES pessoa_fisica (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ocorrency_pf
  OWNER TO convidas;


  -- Table: ocorrency_pj

-- DROP TABLE ocorrency_pj;

CREATE TABLE ocorrency_pj
(
  id integer NOT NULL,
  date timestamp without time zone,
  description character varying(9999),
  pj_id integer,
  CONSTRAINT ocorrency_pj_pkey PRIMARY KEY (id),
  CONSTRAINT fk_8wkxu41iceckf2swj8jhuj8yh FOREIGN KEY (pj_id)
      REFERENCES pessoa_juridica (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ocorrency_pj
  OWNER TO convidas;

  





INSERT INTO cidade(id, name, pais, uf) VALUES (3851, 'Água Santa', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (3864, 'André da Rocha', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (3876, 'Arvorezinha', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (3892, 'Bento Gonçalves', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (3898, 'Bom Jesus', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (3899, 'Bom Princípio', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (3908, 'Caçapava do Sul', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (3909, 'Cacequi', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (3910, 'Cachoeira do Sul', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (3911, 'Cachoeirinha', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (3912, 'Cacique Doble', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (3915, 'Camaquã', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (3916, 'Camargo', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (3918, 'Campestre da Serra', 'Brasil','RS');


INSERT INTO cidade(id, name, pais, uf) VALUES (3931, 'Capão Bonito do Sul', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (3932, 'Capão da Canoa', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (3939, 'Carazinho', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (3940, 'Carlos Barbosa', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (3942, 'Casca', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (3943, 'Caseiros', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (3945, 'Caxias do Sul', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (3959, 'Ciríaco', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (3961, 'Colorado', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (3963, 'Constantina', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (3970, 'Coxilha', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (3974, 'Cruz Alta', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (3977, 'David Canabarro', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (3981, 'Dois Irmãos', 'Brasil','RS');


INSERT INTO cidade(id, name, pais, uf) VALUES (3994, 'Entre Rios do Sul', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (3995, 'Entre-Ijuís', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (3997, 'Erechim', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (3998, 'Ernestina', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (4001, 'Esmeralda', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (4011, 'Farroupilha', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (4016, 'Flores da Cunha', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4017, 'Floriano Peixoto', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4018, 'Fontoura Xavier', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (4022, 'Frederico Westphalen', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4023, 'Garibaldi', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (4027, 'Gentil', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4028, 'Getúlio Vargas', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (4031, 'Gramado', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (4035, 'Guabiju', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (4037, 'Guaporé', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4040, 'Herval', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (4046, 'Ibiaçá', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4047, 'Ibiraiaras', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (4049, 'Ibirubá', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4050, 'Igrejinha', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (4076, 'Júlio de Castilhos', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (4079, 'Lagoa Vermelha', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (4088, 'Machadinho', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (4099, 'Mato Castelhano', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (4108, 'Montenegro', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4109, 'Mormaço', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (4115, 'Muitos Capões', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4116, 'Muliterno', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4117, 'Não-Me-Toque', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4118, 'Nicolau Vergueiro', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4119, 'Nonoai', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4121, 'Nova Araçá', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4122, 'Nova Bassano', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4131, 'Nova Prata', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (4137, 'Novo Hamburgo', 'Brasil','RS');

INSERT INTO cidade(id, name, pais, uf) VALUES (4142, 'Paim Filho', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4143, 'Palmares do Sul', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4144, 'Palmeira das Missões', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4145, 'Palmitinho', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4151, 'Parobé', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4154, 'Passo Fundo', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4160, 'Pelotas', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4163, 'Pinhal da Serra', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4169, 'Planalto', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4171, 'Pontão', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4174, 'Porto Alegre', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4192, 'Rio Grande', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4193, 'Rio Pardo', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4199, 'Ronda Alta', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4204, 'Saldanha Marinho', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4208, 'Sananduva', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4209, 'Santa Bárbara do Sul', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4210, 'Santa Cecília do Sul', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4211, 'Santa Clara do Sul', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4212, 'Santa Cruz do Sul', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4214, 'Santa Maria', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4229, 'Santo Expedito do Sul', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4230, 'São Borja', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4231, 'São Domingos do Sul', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4236, 'São João da Urtiga', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4238, 'São Jorge', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4247, 'São Leopoldo', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4249, 'São Luiz Gonzaga', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4250, 'São Marcos', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4260, 'São Sebastião do Caí', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4261, 'São Sepé', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4262, 'São Valentim', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4268, 'Sapucaia do Sul', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4269, 'Sarandi', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4273, 'Selbach', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4276, 'Serafina Corrêa', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4278, 'Sertão', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4287, 'Tapejara', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4288, 'Tapera', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4297, 'Tio Hugo', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4300, 'Torres', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4301, 'Tramandaí', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4314, 'Tupanci do Sul', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4323, 'Vacaria', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4327, 'Vanini', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4328, 'Venâncio Aires', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4330, 'Veranópolis', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4337, 'Vila Lângaro', 'Brasil','RS');
INSERT INTO cidade(id, name, pais, uf) VALUES (4338, 'Vila Maria', 'Brasil','RS');