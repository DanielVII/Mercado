package Ordenacao;

import model.entity.Produto;

public class Ordenar implements OrdenarInter{

	@Override
	public void OrdenarPorQuantidade(Produto[] produtos) {
		// TODO Auto-generated method stub
		int ta = produtos.length;
    	Produto key;
    	int j;
    	
    	for (int i = 1; i< ta; i++) {
    		key = produtos[i];
    		j = i - 1;
    		
    		while (j >= 0 && produtos[j].getQuantidade() > key.getQuantidade()) {
    			produtos[j+1] = produtos[j];
    			j--;
    		}
    		produtos[j+1] = key;
    	}
	}

	@Override
	public void ReversePorQuantidade(Produto[] produtos) {
		// TODO Auto-generated method stub
		int ta = produtos.length;
    	Produto key;
    	int j;
    	
    	for (int i = 1; i< ta; i++) {
    		key = produtos[i];
    		j = i - 1;
    		
    		while (j >= 0 && produtos[j].getQuantidade() < key.getQuantidade()) {
    			produtos[j+1] = produtos[j];
    			j--;
    		}
    		produtos[j+1] = key;
    	}
	}

}
