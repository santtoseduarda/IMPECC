--SELECT (*)
SELECT * FROM Produtos;

SELECT * FROM Vendas;

SELECT * FROM Historico_de_Vendas;

SELECT * FROM Clientes;

SELECT * FROM Funcionario;

<<<<<<< Updated upstream
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
=======
--WHERE
SELECT * FROM Funcionarios WHERE Email = ? AND Senha = ? ;

--SELECT COM JOIN
SELECT * 
FROM Historico_de_Vendas AS hv 

INNER JOIN Vendas AS v ON hv.id_Vendas = v.id_Vendas

INNER JOIN Produtos AS p ON hv.id_Produtos = p.id_Produtos

INNER JOIN Clientes AS c ON v.id_Clientes = c.id_Clientes

INNER JOIN Funcionarios AS f ON v.id_Funcionarios = f.id_Funcionarios;
>>>>>>> Stashed changes
