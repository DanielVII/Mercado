package model.entity;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
	private List<Produto> itens;
	
	public Carrinho() {
		itens = new ArrayList<>();
	}
	
	public void addItem(Produto prod) {
		boolean EstahNoCarrinho = false;
		
		for( int x = 0; x < this.itens.size(); x++) {
			if(this.itens.get(x).getNome().equals(prod.getNome())) {
				EstahNoCarrinho = true;
				this.itens.get(x).setQuantidade(this.itens.get(x).getQuantidade() + 1);
				break;
			}
		}
		if(!EstahNoCarrinho) {
			Produto p1 = new Produto();
			p1.setCodBarras(prod.getCodBarras());
			p1.setId(prod.getId());
			p1.setMarca(prod.getMarca());
			p1.setNome(prod.getNome());
			p1.setPreco(prod.getPreco());
			p1.setQuantidade(1.0);
			this.itens.add(p1);			
		}
	}
	
	public boolean removeItem(Produto prod) {
		boolean saiu = false;
		for( int x = 0; x < this.itens.size(); x++) {
			if(this.itens.get(x).getNome().equals(prod.getNome())) {
				if(this.itens.get(x).getQuantidade() > 1) {
					this.itens.get(x).setQuantidade(this.itens.get(x).getQuantidade() - 1);
				}else {
					this.itens.remove(x);
				}
				saiu = true;
				break;
			}
		}
		
		return saiu;
	}
	
	public List<Produto> getItens(){
		return this.itens;
	}
	
}
