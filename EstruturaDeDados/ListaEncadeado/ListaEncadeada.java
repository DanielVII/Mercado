package EstruturaDeDados.ListaEncadeado;

public class ListaEncadeada<T> implements ListaEncadeadaInter<T>{
	
	private Noh<T> primeiro;
	private int tamanhoMaximo;
	private int tamanhoAtual;
	
	public ListaEncadeada(int tamanhoLista){
		this.tamanhoMaximo = tamanhoLista;
		this.tamanhoAtual = 0;
	}
	
	@Override
	public boolean addInicio(T info) {
		// TODO A info vai se tornar o Primeiro e o antigo primeiro vai está referenciada no next do novo primeiro
		// Lembrar de mudar o tamanhoAtual e verificar se esse novo add cabe.
		boolean deuCerto = true;
		
		
		return deuCerto;
	}

	@Override
	public boolean addFim(T info) {
		// TODO Vai procurar o noh que aponta pro null e colocar a nova info no next desse noh
		// Lembrar de mudar o tamanhoAtual e verificar se esse novo add cabe.
		boolean deuCerto = true;
		
		
		return deuCerto;
	}

	@Override
	public Noh<T> removeInicio() {
		// TODO Vai tornar o Next do atual Primeiro o novo Primeiro. Então retornar o antigo primeiro, com o next dele apontando pra null
		// Lembrar de mudar o tamanhoAtual
		Noh<T> primeiroRetirado = this.primeiro;
		
		
		return primeiroRetirado;
	}

	public Noh<T> getPrimeiro() {
		return this.primeiro;
	}

	public int getTamanhoMaximo() {
		return this.tamanhoMaximo;
	}

	public int getTamanhoAtual() {
		return this.tamanhoAtual;
	}

	@Override
	public boolean estahVazia() {
		// TODO retorna true se tamanhoatual == 0 e false caso contrario
		return false;
	}

}
