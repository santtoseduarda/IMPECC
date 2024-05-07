INSERT INTO Funcionarios (id_Funcionarios, Nome_Funcionario, Cargo, Email, Senha) VALUES
(?, ?, ?, ?, ?);

INSERT INTO Produtos (id_Produtos, Nome_Produto, Modelo, Tamanho , Genero, Preco, Fornecedor, Qntd_Estoque) VALUES (?, ?, ?, ?, ?, ?, ?, ?);

INSERT INTO Vendas (id_Vendas, Total, Mtd_Pagamento, id_Clientes, id_Funcionarios)
VALUES (?, ?, ?, ?, ?);