package EstruturaDeDados.ListaEncadeado;

public interface ListaEncadeadaInter<T> {
	public boolean addInicio(T info);
	public boolean addFim(T info);
	public boolean estahVazia();
	public Noh<T> removeInicio();
	public Noh<T> removeUltimo();
	public Noh<T> peekInicio();
	public Noh<T> peekUltimo();
}
