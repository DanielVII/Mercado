package EstruturaDeDados.Fila;

import EstruturaDeDados.ListaEncadeado.ListaEncadeada;

public class Fila<T> implements FilaInter<T>{
	
	private ListaEncadeada<T> fila;
	
	public Fila(int tamanhoFila) {
		this.fila = new ListaEncadeada<T>(tamanhoFila);
	}
	
	@Override
	public boolean entrarNaFila(T info) {
		// TODO add por ultimo a info
		return fila.addFim(info);
	}

	@Override
	public T atenderProximo() {
		// TODO remove primeira info da fila
		T conteudo;
		if(fila.estahVazia()) {
			conteudo = null;
		}else {
			conteudo = fila.removeInicio().getInfo();
		}
		return conteudo;
	}

	@Override
	public T proximo() {
		// TODO so o get de quem é o proximo, sem remover
		if(fila.estahVazia()) {
			conteudo = null;
		}else {
			conteudo = fila.peekInicio().getInfo();
		}
		return conteudo;
	}

	@Override
	public int tamanho() {
		// TODO Auto-generated method stub
		return fila.getTamanhoAtual();
	}
}
