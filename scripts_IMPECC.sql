DROP DATABASE  IF EXISTS impecc;

CREATE SCHEMA IF NOT EXISTS impecc;
USE impecc ;

-- -----------------------------------------------------
-- Table impecc.`Clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS impecc.Clientes(
  `id_Cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nome_Cliente` varchar(45) NOT NULL,
  `data_Nasc` varchar(45) NOT NULL,
  `cpf_Cliente` varchar(45) NOT NULL,
  `telefone_Cliente` varchar(45) NOT NULL,
  `email_Cliente` varchar(45) NOT NULL,
  PRIMARY KEY (`id_Cliente`)
);

-- -----------------------------------------------------
-- Table impecc.Fornecedor
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS impecc.Fornecedores(
  `id_Fornecedor` int(11) NOT NULL AUTO_INCREMENT,
  `nome_Fornecedor` varchar(45) NOT NULL,
  `cnpj` varchar(45) NOT NULL,
  `telefone_fornecedor` varchar(45) NOT NULL,
  `email_fornecedor` varchar(45) NOT NULL,
  PRIMARY KEY (`id_Fornecedor`)
) ;


-- -----------------------------------------------------
-- Table impecc.`Funcionarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS impecc.Funcionarios(
  `id_Funcionario` int(11) NOT NULL AUTO_INCREMENT,
  `nome_Funcionario` varchar(45) NOT NULL,
  `email_Funcionario` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `celular` varchar(45) NOT NULL,
  `cpf` varchar(45) NOT NULL,
  PRIMARY KEY (`id_Funcionario`)
) ;
-- -----------------------------------------------------
-- Table impecc.Produtos
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS impecc.Produtos(
  `id_Produto` int(11) NOT NULL AUTO_INCREMENT,
  `nome_Produto` varchar(45) NOT NULL,
  `tamanho` varchar(45) NOT NULL,
  `genero` varchar(45) NOT NULL,
  `preco` decimal(45,0) NOT NULL,
  `fornecedor` varchar(45) NOT NULL,
  `qntd_Estoque` int(11) NOT NULL,
  PRIMARY KEY (`id_Produto`)
) ;


-- -----------------------------------------------------
-- Table impecc.Venda_Produtos
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS impecc.Venda_produtos(
  `id_Venda` int(11) NOT NULL,
  `id_Produto` int(11) NOT NULL,
  `qntd` int(11) NOT NULL,
  `preco` float NOT NULL,
  PRIMARY KEY (`id_Venda`,`id_Produto`),
  KEY `id_Produtofk_idx` (`id_Produto`),
  CONSTRAINT `id_Produtofk` FOREIGN KEY (`id_Produto`) REFERENCES `produtos` (`id_Produto`),
  CONSTRAINT `id_Vendafk` FOREIGN KEY (`id_Venda`) REFERENCES `vendas` (`id_Venda`)
) ;

-- -----------------------------------------------------
-- Table impecc.Vendas
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS impecc.Vendas(
  `id_Venda` int(11) NOT NULL AUTO_INCREMENT,
  `Total` decimal(65,2) NOT NULL,
  `Mtd_Pagamento` varchar(45) NOT NULL,
  `id_Cliente` int(11) NOT NULL,
  `id_Funcionario` int(11) NOT NULL,
  PRIMARY KEY (`id_Venda`),
  KEY `id_Cliente` (`id_Cliente`),
  KEY `id_Funcionario` (`id_Funcionario`),
  CONSTRAINT `vendas_ibfk_1` FOREIGN KEY (`id_Cliente`) REFERENCES `clientes` (`id_Cliente`),
  CONSTRAINT `vendas_ibfk_2` FOREIGN KEY (`id_Funcionario`) REFERENCES `funcionarios` (`id_Funcionario`)
) ;

