package model.entity;

public class Cliente {
	private String nome;
	private Carrinho carrinho;
	
	public Cliente(String nome) {
		this.setNome(nome);
		this.carrinho = new Carrinho();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}
}
