SELECT * FROM Produtos;

SELECT * FROM Funcionarios WHERE Email = ? AND Senha = ? ;

SELECT * FROM Vendas;

SELECT * FROM Historico_de_Vendas;

SELECT * FROM Clientes;

SELECT * FROM Funcionario;

SELECT *
FROM Vendas AS v
INNER JOIN Clientes AS c
INNER JOIN Funcionarios
ON
c.id_Clientes = v.id_Clientes;

SELECT *
FROM Vendas AS v
INNER JOIN Funcionarios AS f
ON
f.id_Funcionario = v.id_Funcionario;