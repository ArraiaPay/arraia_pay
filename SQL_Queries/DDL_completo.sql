CREATE DATABASE IF NOT EXISTS ARRAIAPAY
  DEFAULT CHARSET utf8mb4
  COLLATE utf8mb4_general_ci;

USE ARRAIAPAY;

CREATE TABLE Barraca (
  id_barraca INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  responsavel VARCHAR(100) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE operadores (
  id_operador INT AUTO_INCREMENT PRIMARY KEY,
  nome_operador VARCHAR(100) NOT NULL,
  login_operador VARCHAR(100) NOT NULL UNIQUE,
  senha_hash VARCHAR(255) NOT NULL,
  status_ope ENUM('ativo','bloqueado') NOT NULL DEFAULT 'ativo'
) ENGINE=InnoDB;

CREATE TABLE usuarios (
  id_user INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  login VARCHAR(100) NOT NULL UNIQUE,
  senha_hash VARCHAR(255) NOT NULL,
  id_barraca INT NOT NULL,
  status ENUM('ativo','bloqueado') NOT NULL DEFAULT 'ativo',
  CONSTRAINT usuarios_barraca_fk
    FOREIGN KEY (id_barraca) REFERENCES Barraca (id_barraca)
) ENGINE=InnoDB;

CREATE TABLE cartao (
  id_cartao INT AUTO_INCREMENT PRIMARY KEY,
  codigo_qr VARCHAR(50) NOT NULL UNIQUE,
  nome_titular VARCHAR(100) NOT NULL,
  saldo DECIMAL(10,2) NOT NULL DEFAULT 0,
  status ENUM('ativo','bloqueado') NOT NULL DEFAULT 'ativo',
  CONSTRAINT cartao_saldo_chk CHECK (saldo >= 0)
) ENGINE=InnoDB;

CREATE TABLE recarga (
  id_recarga INT AUTO_INCREMENT PRIMARY KEY,
  id_cartao INT NOT NULL,
  id_operador INT NOT NULL,
  forma_pagamento ENUM('dinheiro','pix','cartao') NOT NULL,
  valor DECIMAL(10,2) NOT NULL,
  data_hora_rec DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  status_rec ENUM('efetivada','cancelada') NOT NULL,
  CONSTRAINT recarga_valor_chk CHECK (valor > 0),
  CONSTRAINT recarga_cartao_fk
    FOREIGN KEY (id_cartao) REFERENCES cartao (id_cartao),
  CONSTRAINT recarga_operador_fk
    FOREIGN KEY (id_operador) REFERENCES operadores (id_operador)
) ENGINE=InnoDB;

CREATE TABLE fechamento_caixa (
  id_fechamento INT AUTO_INCREMENT PRIMARY KEY,
  id_operador INT NOT NULL,
  data_hora_fech DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  saldo_total DECIMAL(10,2) NOT NULL DEFAULT 0,
  dinheiro_total DECIMAL(10,2) NOT NULL DEFAULT 0,
  pix_total DECIMAL(10,2) NOT NULL DEFAULT 0,
  cartao_total DECIMAL(10,2) NOT NULL DEFAULT 0,
  CONSTRAINT fech_operador_fk
    FOREIGN KEY (id_operador) REFERENCES operadores (id_operador)
) ENGINE=InnoDB;

CREATE TABLE Venda (
  id_venda INT AUTO_INCREMENT PRIMARY KEY,
  id_cartao INT NOT NULL,
  id_barraca INT NOT NULL,
  id_user INT NOT NULL,
  valor_total DECIMAL(10,2) NOT NULL,
  data_hora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  status ENUM('pendente','paga','cancelada') NOT NULL DEFAULT 'pendente',
  CONSTRAINT venda_valor_chk CHECK (valor_total > 0),
  CONSTRAINT venda_cartao_fk
    FOREIGN KEY (id_cartao) REFERENCES cartao (id_cartao),
  CONSTRAINT venda_barraca_fk
    FOREIGN KEY (id_barraca) REFERENCES Barraca (id_barraca),
  CONSTRAINT venda_user_fk
    FOREIGN KEY (id_user) REFERENCES usuarios (id_user)
) ENGINE=InnoDB;