package EstruturaDeDados.Fila;

public interface FilaInter<T> {
	public boolean entrarNaFila(T info);
	public T atenderProximo();
	public T proximo();
	public int tamanho();
}
