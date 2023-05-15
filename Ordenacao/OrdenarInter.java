package Ordenacao;

import model.entity.Produto;

public interface OrdenarInter {
	public void OrdenarPorQuantidade(Produto[] produtos);
	public void ReversePorQuantidade(Produto[] produtos);
}
