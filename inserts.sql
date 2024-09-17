-- funcionarios
INSERT INTO Funcionarios (nome_Funcionario, email_Funcionario, celular, cpf, login, senha) VALUES (?, ?, ?, ?, ?, ?);

-- produtos
INSERT INTO Produtos (Nome_Produto, Modelo, Tamanho , Genero, Preco, Fornecedor, Qntd_Estoque) VALUES (?, ?, ?, ?, ?, ?, ?);

-- venda
INSERT INTO Vendas (Total, Mtd_Pagamento)
VALUES (?, ?);

-- cliente
INSERT INTO Clientes (Nome_Cliente, Sobrenome, Data_Nasc, Cpf , Telefone, Email) VALUES (?, ?, ?, ?, ?, ?, ?);

-- historico venda
INSERT INTO Historico_de_Vendas (Qtd_Venda)
VALUES (?);

insert into Produtos (Nome_Produto, Modelo, Tamanho, Genero, Preco, Fornecedor, Qntd_Estoque ) values ('Camisa Preta', 'Cropped', 'PP', 'Feminino', 99.99, 'Nike', 30);
insert into Produtos (Nome_Produto, Modelo, Tamanho, Genero, Preco, Fornecedor, Qntd_Estoque ) values ('Casaco', 'Ziper', 'M', 'Masculino', 135.90, 'Adidas', 25);
insert into Produtos (Nome_Produto, Modelo, Tamanho, Genero, Preco, Fornecedor, Qntd_Estoque ) values ('Top', 'Aberto', 'G', 'Feminino', 179.20, 'Nike', 13);

INSERT INTO Vendas (Total, Mtd_Pagamento)
VALUES (240.95, 'dinheiro');
INSERT INTO Vendas (Total, Mtd_Pagamento)
VALUES (154.96, 'cartão crédito');
INSERT INTO Vendas (Total, Mtd_Pagamento)
VALUES (98.99, 'cartão dépito');

insert into Clientes (Nome_Cliente, Sobrenome, Data_Nasc, Cpf, Telefone, Email) values ('Oliver', 'Abate', '2003-03-25', '664.750.083-48', '(47)99457-6588', 'oabate0@nih.gov');
insert into Clientes (Nome_Cliente, Sobrenome, Data_Nasc, Cpf, Telefone, Email) values ('Shirl', 'Ellinor', '1996-12-6', '391.736.884-56', '(55)99222-6764', 'sellinor1@shutterfly.com');
insert into Clientes (Nome_Cliente, Sobrenome, Data_Nasc, Cpf, Telefone, Email) values ('Simonne', 'Keddle', '2001-02-27', '852.984.540-23', '(35)99030-4782', 'skeddle2@army.mil');

INSERT INTO Historico_de_Vendas (Qtd_Venda)
VALUES (3);
INSERT INTO Historico_de_Vendas (Qtd_Venda)
VALUES (2);
INSERT INTO Historico_de_Vendas (Qtd_Venda)
VALUES (4);

insert into Funcionarios (Nome_Funcionario, Cargo , Email , Senha ) values ('Camili', 'caixa', 'camilli@gmail.com', '479');
insert into Funcionarios (Nome_Funcionario, Cargo , Email , Senha ) values ('Eduarda', 'atendente', 'eduarda@gmail.com', '159');
insert into Funcionarios (Nome_Funcionario, Cargo , Email , Senha ) values ('Isabelle', 'caixa', 'isabelle@gmail.com', '365');
