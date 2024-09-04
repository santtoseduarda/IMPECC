package modelo;

import java.time.LocalDate;

public class Produto {
	
	// id automático
	// id fornecedor

	// tamanho não seria uma lista?
	// exemplos: P - 5 peças | M - 3 peças

	private String nomeProduto;
	private String marca;
	private String modelo;
	private String Gênero;
	private Double preco;
	private Long codBarra;
	private Double custo;
	private Long qtdEstoque;
	private String descProduto;
	private LocalDate dataEntrada;
	
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
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getGênero() {
		return Gênero;
	}
	public void setGênero(String gênero) {
		Gênero = gênero;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Long getCodBarra() {
		return codBarra;
	}
	public void setCodBarra(Long codBarra) {
		this.codBarra = codBarra;
	}
	public Double getCusto() {
		return custo;
	}
	public void setCusto(Double custo) {
		this.custo = custo;
	}
	public Long getQtdEstoque() {
		return qtdEstoque;
	}
	public void setQtdEstoque(Long qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	public String getDescProduto() {
		return descProduto;
	}
	public void setDescProduto(String descProduto) {
		this.descProduto = descProduto;
	}
	public LocalDate getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

}
