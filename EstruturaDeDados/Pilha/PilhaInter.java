package EstruturaDeDados.Pilha;

public interface PilhaInter<T> {
	public boolean empilhar(T info);
	public T desempilhar();
	public T topo();
	public int tamanho();
}
