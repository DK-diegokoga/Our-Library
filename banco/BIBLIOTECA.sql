CREATE DATABASE bdBiblioteca;

go

USE bdBiblioteca;

go

CREATE TABLE tbFUNCIONARIO(
    CODIGO INT PRIMARY KEY,
    NOME VARCHAR (50) NOT NULL,
    EMAIL VARCHAR (200) NOT NULL,
    CONFIRMA_EMAIL VARCHAR (200) NOT NULL,
    PERMISSAO CHAR(10) NOT NULL,
    SENHA VARCHAR (50) NOT NULL,
    CONFIRMA_SENHA VARCHAR(50) NOT NULL
);

CREATE TABLE tbEDITORA(
    CODIGO_EDITORA INT PRIMARY KEY,
    NOME_EDITORA VARCHAR (50) NOT NULL
);

CREATE TABLE tbLIVRO(
    ISBN INT PRIMARY KEY,
    TITULO VARCHAR (50) NOT NULL,
    AUTOR CHAR (15) NOT NULL,
    EDITORA INT, FOREIGN KEY (EDITORA) REFERENCES tbEDITORA(CODIGO_EDITORA),
    QUANTIDADE_ESTOQUE INT,
    QUANTIDADE_DISPONIVEL INT
);

CREATE TABLE tbALUNO(
    RA BIGINT PRIMARY KEY,
    NOME VARCHAR (50) NOT NULL,
    EMAIL VARCHAR (200) NOT NULL,
    CELULAR BIGINT (11) NOT NULL,
    SITUACAO VARCHAR(20),
    PENALIDADE VARCHAR(200),
    DESCRICAO VARCHAR(200)
);

CREATE TABLE tbEMPRESTIMO(
     CODIGO_EMPRESTIMO INT PRIMARY KEY,
     RA_ALUNO BIGINT(13),
     NOME_ALUNO VARCHAR(50),
     CELULAR_ALUNO BIGINT(11),
     CODIGO_LIVRO INT(4),
     TITULO_LIVRO VARCHAR(50),
     DATA_EMPRESTIMO VARCHAR(10),
     DATA_ENTREGA VARCHAR(10),
     SITUACAO_EMPRESTIMO VARCHAR(20)
);

/*Insert da tabela Reserva*/
INSERT INTO tbEMPRESTIMO VALUES(
      1,
      1111111111111,
      'JAPA',
      11949176476,
      1111,
      'O Pequeno Príncipe',
      '01-11-2022',
      '03-11-2022',
      'Regular'
);

/*Insert da tabela Aluno*/
INSERT INTO tbALUNO VALUES(
      1111111111111,
      'JAPA',
      'JAPA',
      11949176476,
      'Regular',
      'Nenhuma',
      'Nenhuma'
);

/*Insert da tabela Funcionario*/

INSERT INTO tbFUNCIONARIO VALUES(
    1,
    'COMUM',
    'COMUN@COMUUM.COM',
    'COMUN@COMUUM.COM',
    'COMUM',
    'COMUM1234',
    'COMUM1234'
);

/*Insert da tabela Editora*/

INSERT INTO tbEDITORA
(CODIGO_EDITORA,NOME_EDITORA)
VALUES
    ( 1, 'Geração Editorial'),
    ( 2, 'Rocco'),
    ( 3, 'Intrínseca'),
    ( 4, 'Pottermore Publishing'),
    ( 5, 'V&R Editoras'),
    ( 6, 'Geração Editorial')
;

/*Insert da tabela Livro*/

INSERT INTO tbLIVRO
    (ISBN, TITULO, AUTOR, EDITORA, QUANTIDADE_ESTOQUE, QUANTIDADE_DISPONIVEL)
VALUES
    (1111,'O Pequeno Príncipe','Antoine de Saint-Exupéry', 1, 10, 2),
    (1112,'Harry Potter e a criança amaldiçoada','J.K. Rowling', 2, 2, 1),
    (1113,'O ladrão de raios(Percy Jackson e os Olimpianos)','Rick Riordan', 3, 5, 4),
    (1114,'ANIMAIS FANTÁSTICOS E ONDE HABITAM','J.K. Rowling', 4, 1, 0),
    (1115,'Diário de um Banana','Jeff Kinney', 5, 8, 2),
    (1116,'A Arte da guerra: Os treze capítulos originais','Sun Tzu', 6, 15, 6)
;
