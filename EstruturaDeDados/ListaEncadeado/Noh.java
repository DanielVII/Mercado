package EstruturaDeDados.ListaEncadeado;

public class Noh<T> {
	private T info;
    private Noh<T> next;

    public Noh(T infoNoh) {
        this.info = infoNoh;
        this.next = null;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T infoNoh) {
    	if(infoNoh != null) {
    		this.info = infoNoh;    		
    	}
    }

    public Noh<T> getNext() {
        return next;
    }

    public void setNext(Noh<T> nextNoh) {
    	if(nextNoh != null) {
    		this.next = nextNoh;    		
    	}
    }
}
