CREATE DATABASE `arraiapay` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

-- arraiapay.cartao definição

CREATE TABLE `cartao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cartao_cartao_fk` (`id_cliente`),
  CONSTRAINT `cartao_cartao_fk` FOREIGN KEY (`id_cliente`) REFERENCES `cartao` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



-- arraiapay.cliente definição

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `saldo` decimal(10,2) NOT NULL,
  `status_cartao` varchar(100) NOT NULL,
  `relacao_escola` varchar(100) NOT NULL,
  `id_cartao` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cliente_cartao` (`id_cartao`),
  CONSTRAINT `fk_cliente_cartao` FOREIGN KEY (`id_cartao`) REFERENCES `cartao` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
