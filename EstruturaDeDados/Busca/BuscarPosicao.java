package Busca;

import model.entity.Produto;

public class BuscarPosicao implements BuscarPosicaoInter {
	private Produto[] produtos;
	
	public BuscarPosicao(Produto[] produtos) {
		this.produtos = produtos;
	}
	@Override
	public int BuscarPorNome(String nome) {
		// TODO Auto-generated method stub
		int posicao = -1;
		
		for (int x = 0; x < this.produtos.length; x++) {
			if(this.produtos[x].getNome().equals(nome)) {
				posicao = x;
				break;
			}
		}
		return posicao;
	}

}
