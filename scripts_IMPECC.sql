DROP DATABASE IF EXISTS impecc;

CREATE SCHEMA IF NOT EXISTS impecc;
USE impecc ;

-- -----------------------------Fornecedor------------------------
CREATE TABLE IF NOT EXISTS impecc.Fornecedor (
	id_Fornecedor INT NOT NULL AUTO_INCREMENT,
	nome_Fornecedor VARCHAR(45) NOT NULL,
    cnpj VARCHAR(45) NOT NULL,
	telefone_fornecedor VARCHAR(45) NOT NULL,
	email_fornecedor VARCHAR(45) NOT NULL,
	PRIMARY KEY (id_Fornecedor)
);

-- -----------------------------------------------------
-- Table impecc.Produtos
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS impecc.Produtos (
  id_Produto INT NOT NULL AUTO_INCREMENT,
  nome_Produto VARCHAR(45) NOT NULL,
  modelo VARCHAR(45) NOT NULL,
  tamanho VARCHAR(45) NOT NULL,
  genero VARCHAR(45) NOT NULL,
  preco DECIMAL(65, 2) NOT NULL,
  qntd_Estoque INT NOT NULL,
  id_Fornecedor INT NOT NULL,
  PRIMARY KEY (id_Produto, id_Fornecedor),
	FOREIGN KEY (id_Fornecedor) REFERENCES impecc.Fornecedor (id_Fornecedor)
  );

-- -----------------------------------------------------
-- Table impecc.Clientes
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS impecc.Clientes (
  id_Cliente INT NOT NULL AUTO_INCREMENT,
  nome_Cliente VARCHAR(45) NOT NULL,
  data_Nasc DATE NOT NULL,
  cpf VARCHAR(45) NOT NULL,
  telefone_Cliente VARCHAR(45) NOT NULL,
  email_Cliente VARCHAR(45) NOT NULL,
  PRIMARY KEY (id_Cliente)
  );

-- -----------------------------------------------------
-- Table impecc.Funcionarios
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS impecc.Funcionarios (
  id_Funcionario INT NOT NULL AUTO_INCREMENT,
  nome_Funcionario VARCHAR(45) NOT NULL,
  email_Funcionario VARCHAR(45) NOT NULL,
  login VARCHAR(45) NOT NULL,
  senha VARCHAR(45) NOT NULL,
  celular VARCHAR(45) NOT NULL,
  cpf VARCHAR(45) NOT NULL,
  PRIMARY KEY (id_Funcionario)
  );

-- -----------------------------------------------------
-- Table impecc.Vendas
CREATE TABLE IF NOT EXISTS impecc.Vendas (
  id_Venda INT NOT NULL AUTO_INCREMENT,
  Total DECIMAL(65, 2) NOT NULL,
  Mtd_Pagamento VARCHAR(45) NOT NULL,
  id_Cliente INT NOT NULL,
  id_Funcionario INT NOT NULL,
  id_Produto INT NOT NULL,
  PRIMARY KEY (id_Venda, id_Cliente, id_Funcionario, id_Produto),
	FOREIGN KEY (id_Cliente) REFERENCES impecc.Clientes (id_Cliente),
    FOREIGN KEY (id_Funcionario) REFERENCES impecc.Funcionarios (id_Funcionario),
	FOREIGN KEY (id_Produto) REFERENCES impecc.Produtos (id_Produto)
    );
