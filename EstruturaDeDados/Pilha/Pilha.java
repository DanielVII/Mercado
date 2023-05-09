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
		boolean empilhou = pilha.addInicio(info);
		return empilhou;
	}

	@Override
	public T desempilhar() {
		// TODO remove a primeira info e retorna ela
		
		T conteudo;
		if(pilha.estahVazia()) {
			conteudo = null;
		}else {
			conteudo = pilha.removeInicio().getInfo();
		}
		return conteudo;
	}

	@Override
	public T topo() {
		// TODO get a primeira info sem remove
		T info;
		if(pilha.estahVazia()) {
			info = null;
		}else {
			info = pilha.peekInicio().getInfo();
			
		}
		return info;
	}

	@Override
	public int tamanho() {
		// TODO Auto-generated method stub
		return pilha.getTamanhoAtual();
	}

}
