DROP DATABASE IF EXISTS impecc;

CREATE DATABASE IF NOT EXISTS impecc;

USE impecc;

-- -----------------------------------------------------
-- Table mydb.Produtos
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mydb.Produtos (
    id_Produtos INT NOT NULL,
    Nome_Produto VARCHAR(45) NOT NULL,
    Modelo VARCHAR(45) NOT NULL,
    Tamanho VARCHAR(45) NOT NULL,
    Genero VARCHAR(45) NOT NULL,
    Preco DECIMAL NULL,
    Fornecedor VARCHAR(45) NOT NULL,
    Qntd_Estoque INT NOT NULL,
    PRIMARY KEY (id_Produtos)
);

-- -----------------------------------------------------
-- Table mydb.Clientes
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mydb.Clientes (
    id_Clientes INT NOT NULL,
    Nome_Cliente VARCHAR(45) NOT NULL,
    Sobrenome VARCHAR(45) NOT NULL,
    Data_Nasc DATE NOT NULL,
    Cpf VARCHAR(45) NOT NULL,
    Telefone VARCHAR(45) NOT NULL,
    Email VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_Clientes)
);

-- -----------------------------------------------------
-- Table mydb.Funcionarios
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mydb.Funcionarios (
    id_Funcionarios INT NOT NULL,
    Nome_Funcionario VARCHAR(45) NOT NULL,
    Cargo VARCHAR(45) NOT NULL,
    Email VARCHAR(45) NOT NULL,
    Senha VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_Funcionarios)
);

-- -----------------------------------------------------
-- Table mydb.Vendas
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mydb.Vendas (
    id_Vendas INT NOT NULL,
    Total DECIMAL NOT NULL,
    Mtd_Pagamento VARCHAR(45) NOT NULL,
    id_Clientes INT NOT NULL,
    id_Funcionarios INT NOT NULL,
    PRIMARY KEY (id_Vendas, id_Clientes, id_Funcionarios),
    FOREIGN KEY (id_Clientes) REFERENCES mydb.Clientes (id_Clientes),
    FOREIGN KEY (id_Funcionarios) REFERENCES mydb.Funcionarios (id_Funcionarios)
);

-- -----------------------------------------------------
-- Table mydb.Historico_de_Vendas
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mydb.Historico_de_Vendas (
    id_Historico_de_Vendas INT NOT NULL,
    Qtd_Venda INT NOT NULL,
    id_Vendas INT NOT NULL,
    id_Produtos INT NOT NULL,
    PRIMARY KEY (id_Historico_de_Vendas, id_Vendas, id_Produtos),
    FOREIGN KEY (id_Vendas) REFERENCES mydb.Vendas (id_Vendas),
    FOREIGN KEY (id_Produtos) REFERENCES mydb.Produtos (id_Produtos)
);
