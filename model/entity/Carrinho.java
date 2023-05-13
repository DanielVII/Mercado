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
			if(this.itens.get(x).getNome() == prod.getNome()) {
				EstahNoCarrinho = true;
				this.itens.get(x).setQuantidade(this.itens.get(x).getQuantidade() + 1);
				break;
			}
		}
		if(!EstahNoCarrinho) {
			this.itens.add(prod);			
		}
	}
	
	public void removeItem(Produto prod) {
		for( int x = 0; x < this.itens.size(); x++) {
			if(this.itens.get(x).getNome() == prod.getNome()) {
				if(this.itens.get(x).getQuantidade() > 1) {
					this.itens.get(x).setQuantidade(this.itens.get(x).getQuantidade() - 1);
				}else {
					this.itens.remove(x);
				}
				break;
			}
		}
	}
}
