-- funcionarios
INSERT INTO funcionarios (nome_Funcionario, email_Funcionario, celular, cpf, login, senha) VALUES (?, ?, ?, ?, ?, ?)

-- cliente
INSERT INTO clientes (nome_Cliente, data_Nasc, cpf_Cliente, telefone_Cliente, email_Cliente) VALUES (?, ?, ?, ?, ?)

-- fornecedor
INSERT INTO fornecedor (nome_Fornecedor, cnpj, telefone_fornecedor, email_fornecedor) VALUES (?, ?, ?, ?)

-- produtos
INSERT INTO produtos (Nome_Produto, Modelo, Tamanho , Genero, Preco, Fornecedor, Qntd_Estoque) VALUES (?, ?, ?, ?, ?, ?, ?);

-- vendas
INSERT INTO vendas (Total, Mtd_Pagamento) VALUES (?, ?);


INSERT INTO produtos (nome_Produto, tamanho, genero, preco, fornecedor, qntd_Estoque ) values ('Camisa Preta', 'PP', 'Feminino', 99.99, 'Nike', 30);
INSERT INTO produtos (nome_Produto, tamanho, genero, preco, fornecedor, qntd_Estoque ) values ('Camisa Regata Cinza', 'M', 'Masculino', 79.90, 'Delta', 12);
INSERT INTO produtos (nome_Produto, tamanho, genero, preco, fornecedor, qntd_Estoque ) values ('Calça Legging Preta', 'M', 'Feminino', 89.99, 'Adidas', 12);
INSERT INTO produtos (nome_Produto, tamanho, genero, preco, fornecedor, qntd_Estoque ) values ('Top Curve Barrado', 'GG', 'Feminino', 49.50, 'Adidas', 99);
INSERT INTO produtos (nome_Produto, tamanho, genero, preco, fornecedor, qntd_Estoque ) values ('Saia Esportiva', 'P', 'Feminino', 79.99, 'Gamma', 30);

INSERT INTO funcionarios (nome_Funcionario, email_Funcionario, celular, cpf, login, senha) VALUES ('Isabelle Kohler', '2015belle@gmail.com', '(47)99101-8455', '456.654.456-98', 'asd', 'asd');
INSERT INTO funcionarios (nome_Funcionario, email_Funcionario, celular, cpf, login, senha) VALUES ('Carlos Pereira', 'carlos.pereira@gmail.com', '(42)98765-4323', '345.678.901-23', 'carlos', '4321')
INSERT INTO funcionarios (nome_Funcionario, email_Funcionario, celular, cpf, login, senha) VALUES ('Paulo Strey', 'pauloStrey@gmail.com', '(47)99756-2625', '056.678.154-22', 'paulo', 'paulo')
INSERT INTO funcionarios (nome_Funcionario, email_Funcionario, celular, cpf, login, senha) VALUES ('Camili Naara', 'milli.ns@gmail.com', '(41)99658-0203', '412.656.324-98', 'camili', 'camili')
INSERT INTO funcionarios (nome_Funcionario, email_Funcionario, celular, cpf, login, senha) VALUES ('Eduarda Santos', 'duda.santos@gmail.com', '(47)99155-2365', '103.522.632-90', 'duda', 'duda')

INSERT INTO clientes (nome_Cliente, data_Nasc, cpf_Cliente, telefone_Cliente, email_Cliente) VALUES ('Maria Oliveira', '15/07/1985', '123.456.789-00', '(21)98765-4321', 'maria.oliveira@email.com')
INSERT INTO clientes (nome_Cliente, data_Nasc, cpf_Cliente, telefone_Cliente, email_Cliente) VALUES ('João Silva', '23/11/1990', '234.567.890-11', '(11)91234-5678', 'joao.silva@email.com')
INSERT INTO clientes (nome_Cliente, data_Nasc, cpf_Cliente, telefone_Cliente, email_Cliente) VALUES ('Carlos Pereira', '29/09/1978', '456.789.012-33', '(47)93456-7890', 'carlos.pereira@email.com')
INSERT INTO clientes (nome_Cliente, data_Nasc, cpf_Cliente, telefone_Cliente, email_Cliente) VALUES ('Ana Souza', '05/03/1982', '345.678.901-22', '(31)99876-5432', 'ana.souza@email.com')
INSERT INTO clientes (nome_Cliente, data_Nasc, cpf_Cliente, telefone_Cliente, email_Cliente) VALUES ('Beatriz Lima', '12/12/1995', '567.890.123-44', '(85)92345-6789', 'beatriz.lima@email.com')

INSERT INTO fornecedor (nome_Fornecedor, cnpj, telefone_fornecedor, email_fornecedor) VALUES ('Alpha Distribuidora', '12.345.678/0001-90', '(11)98765-4321', 'contato@alpha.com')
INSERT INTO fornecedor (nome_Fornecedor, cnpj, telefone_fornecedor, email_fornecedor) VALUES ('Delta', '45.678.901/0001-23', '(47)93456-7890', 'delta@comercio.com')
INSERT INTO fornecedor (nome_Fornecedor, cnpj, telefone_fornecedor, email_fornecedor) VALUES ('Nike', '56.789.012/0001-34', '(85)92345-6789', 'nike@consor.com')
INSERT INTO fornecedor (nome_Fornecedor, cnpj, telefone_fornecedor, email_fornecedor) VALUES ('Gamma', '34.567.890/0001-12', '(31)99876-5432', 'gamma@materiais.com')
INSERT INTO fornecedor (nome_Fornecedor, cnpj, telefone_fornecedor, email_fornecedor) VALUES ('Adidas', '12.345.678/0001-90', '(11)98765-4321', 'adidas@org.com')

INSERT INTO vendas (total, mtd_Pagamento) VALUES (240.95, 'dinheiro');
INSERT INTO vendas (total, mtd_Pagamento) VALUES (240.95, 'dinheiro');
INSERT INTO vendas (total, mtd_Pagamento) VALUES (154.96, 'cartão crédito');
INSERT INTO vendas (total, mtd_Pagamento) VALUES (98.99, 'cartão dépito');
INSERT INTO vendas (total, mtd_Pagamento) VALUES (154.96, 'cartão crédito');