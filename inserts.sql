INSERT INTO Funcionarios (id_Funcionarios, Nome_Funcionario, Cargo, Email, Senha) VALUES
(?, ?, ?, ?, ?);

INSERT INTO Produtos (id_Produtos, Nome_Produto, Modelo, Tamanho , Genero, Preco, Fornecedor, Qntd_Estoque) VALUES (?, ?, ?, ?, ?, ?, ?, ?);

INSERT INTO Vendas (id_Vendas, Total, Mtd_Pagamento, id_Clientes, id_Funcionarios)
VALUES (?, ?, ?, ?, ?);

INSERT INTO Clientes (id_Clientes, Nome_Cliente, Sobrenome, Data_Nasc, Cpf , Telefone, Email) VALUES (?, ?, ?, ?, ?, ?, ?);

insert into Produtos (Nome_Produto, Modelo, Tamanho, Genero, Preco, Fornecedor, Qntd_Estoque ) values ('Camisa Preta', 'Cropped', 'PP', 'Feminino', '99.99', 'Nike', 30);
insert into Produtos (Nome_Produto, Modelo, Tamanho, Genero, Preco, Fornecedor, Qntd_Estoque ) values ('Casaco', 'Ziper', 'M', 'Masculino', '135.90', 'Adidas', 25);
insert into Produtos (Nome_Produto, Modelo, Tamanho, Genero, Preco, Fornecedor, Qntd_Estoque ) values ('Top', 'Aberto', 'G', 'Feminino', '179.20', 'Nike', 13);
