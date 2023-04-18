package EstruturaDeDados.Pilha;

import EstruturaDeDados.ListaEncadeado.ListaEncadeada;

public class Pilha<T> implements PilhaInter<T> {

	private ListaEncadeada<T> pilha;
	
	public Pilha(int tamanhoPilha) {
		pilha = new ListaEncadeada<T>(tamanhoPilha);
	}
	
	@Override
	public boolean empilhar(T info) {
		// TODO add no começo a info e retorna true se deu certo
		return false;
	}

	@Override
	public T desempilhar() {
		// TODO remove a primeira info e retorna ela
		return null;
	}

	@Override
	public T topo() {
		// TODO get a primeira info sem remove
		return null;
	}

	public ListaEncadeada<T> getPilha() {
		return pilha;
	}

}
