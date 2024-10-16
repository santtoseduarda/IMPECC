package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Produto {

	private int id_Produto;
	private String nomeProduto;
	private String marca;
	private String genero;
	private float preco;
	private String codBarra;
	private int qtdEstoque;
	private String tamanho;
	private String fornecedor;

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
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

	public String getCodBarra() {
		return codBarra;
	}

	public void setCodBarra(String codBarra) {
		this.codBarra = codBarra;
	}

	public int getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(int qntEstoqueConvert) {
		this.qtdEstoque = qntEstoqueConvert;
	}
}
