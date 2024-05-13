DROP DATABASE IF EXISTS impecc;

CREATE SCHEMA IF NOT EXISTS impecc;
USE impecc ;

-- -----------------------------------------------------
-- Table impecc.`Produtos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS impecc.`Produtos` (
  `id_Produtos` INT NOT NULL AUTO_INCREMENT,
  `Nome_Produto` VARCHAR(45) NOT NULL,
  `Modelo` VARCHAR(45) NOT NULL,
  `Tamanho` VARCHAR(45) NOT NULL,
  `Genero` VARCHAR(45) NOT NULL,
  `Preco` DECIMAL NULL,
  `Fornecedor` VARCHAR(45) NOT NULL,
  `Qntd_Estoque` INT NOT NULL,
  PRIMARY KEY (`id_Produtos`))

-- -----------------------------------------------------
-- Table impecc.`Clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS impecc.`Clientes` (
  `id_Clientes` INT NOT NULL AUTO_INCREMENT,
  `Nome_Cliente` VARCHAR(45) NOT NULL,
  `Data_Nasc` DATE NOT NULL,
  `Cpf` VARCHAR(45) NOT NULL,
  `Telefone` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_Clientes`))

-- -----------------------------------------------------
-- Table impecc.`Funcionarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS impecc.`Funcionarios` (
  `id_Funcionarios` INT NOT NULL AUTO_INCREMENT,
  `Nome_Funcionario` VARCHAR(45) NOT NULL,
  `Cargo` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Senha` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_Funcionarios`))

-- -----------------------------------------------------
-- Table impecc.`Vendas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS impecc.`Vendas` (
  `id_Vendas` INT NOT NULL AUTO_INCREMENT,
  `Total` DECIMAL NOT NULL,
  `Mtd_Pagamento` VARCHAR(45) NOT NULL,
  `id_Clientes` INT NOT NULL,
  `id_Funcionarios` INT NOT NULL,
  PRIMARY KEY (`id_Vendas`, `id_Clientes`, `id_Funcionarios`),
    FOREIGN KEY (`id_Clientes`) REFERENCES impecc.`Clientes` (`id_Clientes`),
    FOREIGN KEY (`id_Funcionarios`) REFERENCES impecc.`Funcionarios` (`id_Funcionarios`)
    );

-- -----------------------------------------------------
-- Table impecc.`Historico_de_Vendas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS impecc.`Historico_de_Vendas` (
  `id_Historico_de_Vendas` INT NOT NULL AUTO_INCREMENT,
  `Qtd_Venda` INT NOT NULL,
  `id_Vendas` INT NOT NULL,
  `id_Produtos` INT NOT NULL,
  PRIMARY KEY (`id_Historico_de_Vendas`, `id_Vendas`, `id_Produtos`),
    FOREIGN KEY (`id_Vendas`) REFERENCES impecc.`Vendas` (`id_Vendas`),
    FOREIGN KEY (`id_Produtos`) REFERENCES impecc.`Produtos` (`id_Produtos`)
    );
