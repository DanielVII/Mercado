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
		return false;
	}

	@Override
	public T atenderProximo() {
		// TODO remove primeira info da fila
		return null;
	}

	@Override
	public T proximo() {
		// TODO so o get de quem é o proximo, sem remover
		return null;
	}

	public ListaEncadeada<T> getFila() {
		return fila;
	}

}
