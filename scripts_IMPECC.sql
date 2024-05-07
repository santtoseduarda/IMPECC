DROP DATABASE IF EXISTS impecc;

CREATE DATABASE IF NOT EXISTS impecc;

USE impecc;

-- -----------------------------------------------------
-- Table Produtos
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Produtos (
    id_Produtos INT NOT NULL,
    Nome_Cliente VARCHAR(45) NOT NULL,
    Modelo VARCHAR(45) NOT NULL,
    Tamanho VARCHAR(45) NOT NULL,
    Genero VARCHAR(45) NOT NULL,
    Preco DECIMAL NULL,
    Fornecedor VARCHAR(45) NOT NULL,
    Qntd_Estoque INT NOT NULL,
    PRIMARY KEY (id_Produtos)
);

-- -----------------------------------------------------
-- Table Clientes
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Clientes (
    id_Clientes INT NOT NULL,
    Nome_Cliente VARCHAR(45) NOT NULL,
    Data_Nasc DATE NOT NULL,
    Cpf VARCHAR(45) NOT NULL,
    Telefone VARCHAR(45) NOT NULL,
    Email VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_Clientes)
);

-- -----------------------------------------------------
-- Table Funcionarios
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Funcionarios (
    id_Funcionarios INT NOT NULL,
    Nome_Funcionario VARCHAR(45) NOT NULL,
    Cargo VARCHAR(45) NOT NULL,
    Email VARCHAR(45) NOT NULL,
    Senha VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_Funcionarios)
);

-- -----------------------------------------------------
-- Table Vendas
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Vendas (
    id_Vendas INT NOT NULL,
    Total DECIMAL NOT NULL,
    Mtd_Pagamento VARCHAR(45) NOT NULL,
    id_Clientes INT NOT NULL,
    id_Funcionarios INT NOT NULL,
    PRIMARY KEY (id_Vendas, id_Clientes, id_Funcionarios),
    FOREIGN KEY (id_Clientes) REFERENCES Clientes (id_Clientes),
    FOREIGN KEY (id_Funcionarios) REFERENCES Funcionarios (id_Funcionarios)
);

-- -----------------------------------------------------
-- Table Historico_de_Vendas
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Historico_de_Vendas (
    id_Historico_de_Vendas INT NOT NULL,
    Qtd_Venda INT NOT NULL,
    id_Vendas INT NOT NULL,
    id_Produtos INT NOT NULL,
    PRIMARY KEY (id_Historico_de_Vendas, id_Vendas, id_Produtos),
    FOREIGN KEY (id_Vendas) REFERENCES Vendas (id_Vendas),
    FOREIGN KEY (id_Produtos) REFERENCES Produtos (id_Produtos)
);