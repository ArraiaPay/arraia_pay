CREATE TABLE ArraiaPay.`Barraca (G3)` (
	id_barraca INT auto_increment NOT NULL,
	nome varchar(100) NOT NULL,
	responsavel varchar(100) NOT NULL,
	CONSTRAINT Barraca_G3__pk PRIMARY KEY (id_barraca)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;



CREATE TABLE ArraiaPay.`Venda (G4)` (
	id_venda INT auto_increment NOT NULL,
	id_cartao INT NULL,
	id_barraca INT NULL,
	id_operador INT NULL,
	valor_total DECIMAL NOT NULL,
	data_hora DATETIME NOT NULL,
	status varchar(100) NOT NULL,
	CONSTRAINT Venda_G4__pk PRIMARY KEY (id_venda)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
