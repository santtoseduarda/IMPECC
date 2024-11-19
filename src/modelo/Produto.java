package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Produto {

	private int id_Produto;
	private String nomeProduto;
	private String marca;
	private String genero;
	private float preco;
	private int codBarra;
	private int qtdEstoque;
	private String tamanho;
	private Fornecedor fornecedor;

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public int getId_Produto() {
		return id_Produto;
	}

	public void setId_Produto(int id_Produto) {
		this.id_Produto = id_Produto;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float precoConvert) {
		this.preco = precoConvert;
	}

	public int getCodBarra() {
		return codBarra;
	}

	public void setCodBarra(int codBarraConvert) {
		this.codBarra = codBarraConvert;
	}

	public int getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(int qntEstoqueConvert) {
		this.qtdEstoque = qntEstoqueConvert;
	}

}
