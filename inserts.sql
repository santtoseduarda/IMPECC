-- funcionarios
INSERT INTO Funcionarios (Nome_Funcionario, Cargo, Email, Senha) VALUES
(?, ?, ?, ?);

-- produtos
INSERT INTO Produtos (Nome_Produto, Modelo, Tamanho , Genero, Preco, Fornecedor, Qntd_Estoque) VALUES (?, ?, ?, ?, ?, ?, ?);
insert into Produtos (Nome_Produto, Modelo, Tamanho, Genero, Preco, Fornecedor, Qntd_Estoque ) values ('Camisa Preta', 'Cropped', 'PP', 'Feminino', '99.99', 'Nike', 30);
insert into Produtos (Nome_Produto, Modelo, Tamanho, Genero, Preco, Fornecedor, Qntd_Estoque ) values ('Casaco', 'Ziper', 'M', 'Masculino', '135.90', 'Adidas', 25);
insert into Produtos (Nome_Produto, Modelo, Tamanho, Genero, Preco, Fornecedor, Qntd_Estoque ) values ('Top', 'Aberto', 'G', 'Feminino', '179.20', 'Nike', 13);

-- venda
INSERT INTO Vendas (Total, Mtd_Pagamento)
VALUES (?, ?);
INSERT INTO Vendas (Total, Mtd_Pagamento)
VALUES ('240.95', 'dinheiro');
INSERT INTO Vendas (Total, Mtd_Pagamento)
VALUES ('154.96', 'cartão crédito');
INSERT INTO Vendas (Total, Mtd_Pagamento)
VALUES ('98.99', 'cartão dépito');

-- cliente
INSERT INTO Clientes (Nome_Cliente, Sobrenome, Data_Nasc, Cpf , Telefone, Email) VALUES (?, ?, ?, ?, ?, ?, ?);

-- historico venda
INSERT INTO Historico_de_Vendas (Qtd_Venda)
VALUES (?);
INSERT INTO Historico_de_Vendas (Qtd_Venda)
VALUES (3);
INSERT INTO Historico_de_Vendas (Qtd_Venda)
VALUES (2);
INSERT INTO Historico_de_Vendas (Qtd_Venda)
VALUES (4);
