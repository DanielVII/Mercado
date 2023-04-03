package BancoDeDados;

import model.entity.*;

import java.util.ArrayList;
import java.util.List;

import model.Service.*;

public class Popular {
	private ProdutoBO prodBO = new ProdutoBO();

	public void CriarProduto(String nome, String marca, String cod_de_barras, double quantidade, double preco) {
		Produto prod = new Produto();
		prod.setNome(nome);
		prod.setMarca(marca);
		prod.setCodBarras(cod_de_barras);
		prod.setQuantidade(quantidade);
		prod.setPreco(preco);
		
		this.prodBO.inserir(prod);
	}
	public static void main(String[] args) {
		Popular p = new Popular();

		
		p.CriarProduto("bolacha", "original", "111", 8, 10.0);
		p.CriarProduto("biscoito", "falsificada", "222", 11, 20);
		p.CriarProduto("refri", "coca", "333", 20, 15);

	}
}
