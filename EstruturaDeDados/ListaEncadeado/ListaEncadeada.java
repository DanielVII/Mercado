package EstruturaDeDados.ListaEncadeado;

public class ListaEncadeada<T> implements ListaEncadeadaInter<T>{
	
	private Noh<T> primeiro;
	private Noh<T> ultimo;
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
		if (this.tamanhoAtual == this.tamanhoMaximo) {
	        deuCerto = false;
	    }
		else {
			Noh<T> newNode = new Noh<>(info);
			
			if (primeiro == null) {
				primeiro = newNode;
				ultimo = newNode;
			} else {
				newNode.setNext(primeiro);
				primeiro = newNode;
			}
			
			this.tamanhoAtual++;
		}
		
		return deuCerto;
	}

	@Override
	public boolean addFim(T info) {
		// TODO Vai procurar o noh que aponta pro null e colocar a nova info no next desse noh
		// Lembrar de mudar o tamanhoAtual e verificar se esse novo add cabe.
		boolean deuCerto = true;
		if (this.tamanhoAtual == this.tamanhoMaximo) {
	        deuCerto = false;
	    }
		else {
			Noh<T> newNode = new Noh<>(info);
			
			if (primeiro == null) {
				primeiro = newNode;
				ultimo = newNode;
			} else {
				ultimo.setNext(newNode);
				ultimo = newNode;
			}
			
			this.tamanhoAtual++;
		}
		
		return deuCerto;
	}

	@Override
	public Noh<T> removeInicio() {
		// TODO Vai tornar o Next do atual Primeiro o novo Primeiro. Então retornar o antigo primeiro, com o next dele apontando pra null
		// Lembrar de mudar o tamanhoAtual
		Noh<T> primeiroRetirado = this.primeiro;
		 if (primeiro == null) {
		        primeiroRetirado = null; //lista vazia
		    }
		 else {
			 if (primeiro == ultimo) {
				 primeiro = null;
				 ultimo = null;
			 } else {
				 primeiro = primeiro.getNext();
			 }
			 
			 this.tamanhoAtual--;
			 
		 }

		
		return primeiroRetirado;
	}

	@Override
	public Noh<T> removeUltimo() {
		Noh<T> ultimoRetirado = this.ultimo;
		 if (primeiro == null) {
		        ultimoRetirado = null; //lista vazia
		    }
		 else {
			 if (primeiro == ultimo) {
				 primeiro = null;
				 ultimo = null;
			 } else if(primeiro.getNext() == ultimo){
				 ultimo = primeiro;
			 }else {
				 Noh<T> penultimo = primeiro;
				 while(penultimo.getNext() != ultimo) {
					 penultimo = penultimo.getNext();
				 }
				 ultimo = penultimo;
			 }
			 
			 this.tamanhoAtual--;
			 
		 }

		
		return ultimoRetirado;
	}

	@Override
	public Noh<T> peekInicio() {
		// TODO Auto-generated method stub
		return primeiro;
	}

	@Override
	public Noh<T> peekUltimo() {
		// TODO Auto-generated method stub
		return ultimo;
	}

	@Override
	public boolean estahVazia() {
		// TODO retorna true se tamanhoatual == 0 e false caso contrario
		boolean vazia = false;
		if(primeiro == null) {
			vazia = true;
		}
		return vazia;
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

	public Noh<T> getUltimo() {
		return ultimo;
	}

}
